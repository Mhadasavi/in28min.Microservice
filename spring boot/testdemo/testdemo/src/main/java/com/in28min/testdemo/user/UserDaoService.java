package com.in28min.testdemo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> list=new ArrayList<>();
	static int countUser=3;
	
	  {
		list.add(new User(1,"A", new Date()));
		list.add(new User(2,"B", new Date()));
		list.add(new User(3,"C", new Date()));
	}
	
	public List<User> getAll(){
		return list;
	}
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++countUser);
		}
		list.add(user);
		return user;
	}
	
	public User get(int id) {
		for(User user:list) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User delete(int id) {
		Iterator<User> itr=list.iterator();
		
		while(itr.hasNext()) {
			User user=itr.next();
			if(user.getId()==id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}
}
