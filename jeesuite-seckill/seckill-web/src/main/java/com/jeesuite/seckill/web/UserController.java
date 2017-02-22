/**
 * 
 */
package com.jeesuite.seckill.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jeesuite.seckill.api.IUserService;
import com.jeesuite.seckill.dto.User;

/**
 * @description <br>
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @date 2017年1月15日
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody WrapperResponseEntity get(@PathVariable(value="id") int userId) {
        System.out.println("--    -------------------");
        userService.kafkaProducerTest();
        System.out.println(userId);
        User user = userService.getUser(userId);
        System.out.println("---------334---");
        System.out.println(user.getName());
        return new WrapperResponseEntity("200", null, user);
    }
    
    
    @RequestMapping(value="/lists",method = RequestMethod.GET)
    public ModelAndView getAllUsers(ModelAndView mv){
        System.out.println("11111111111111111111111");
    	List<User> users = new ArrayList<>();
    	for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setId(1000+i);
			user.setName("user"+i);
			user.setMobile("1380013800"+i);
			user.setCreatedAt(new Date());
			users.add(user);
		}
    	mv.addObject("users", users);
        mv.setViewName("user/list");
        return mv;
    }
    

}
