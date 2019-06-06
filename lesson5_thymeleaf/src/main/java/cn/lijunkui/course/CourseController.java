package cn.lijunkui.course;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lijunkui.course.pojo.User;

@Controller
@RequestMapping("/course")
public class CourseController {
	@RequestMapping("/th")
	public String th(Model model){
		String msg = "<h1>我是h1</h1>";
		model.addAttribute("msg",msg);
		model.addAttribute("a",1);
		model.addAttribute("b",2);
		model.addAttribute("flag",true);
		User user = new User("ljk",23);
		model.addAttribute("user",user);
		return "/course/th";
	}
	@RequestMapping("/thif")
	public String thif(Model model){
		model.addAttribute("flag",true);
		return "/course/thif";
	}
	@RequestMapping("/thswitch")
	public String thswitch(Model model){
		User user = new User("ljk",23);
		model.addAttribute("user",user);
		return "/course/thswitch";
	}
	@RequestMapping("/theach")
	public String theach(Model model){
		
		List<User> userList = new ArrayList<User>();
		User user1 = new User("ljk",18);
		User user2 = new User("ljk2",19);
		User user3 = new User("ljk3",20);
		User user4 = new User("lj4",21);
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		model.addAttribute("userList",userList);
		
		List<String> strList = new ArrayList<String>();
		strList.add("ljk");
		strList.add("ljk2");
		strList.add("ljk3");
		strList.add("lj4");
		model.addAttribute("strList",strList);
		
		return "/course/theach";
	}
	@RequestMapping("/dates")
	public String dates(Model model) throws ParseException{
		Date date = new Date();
		model.addAttribute("date",date);
		
		String dateStr = "2018-05-30";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 =  sdf.parse(dateStr); 
		Date[] datesArray = new Date[2];
		datesArray[0] = date;
		datesArray[1] = date2;
		model.addAttribute("datesArray",datesArray);
		
		List<Date> datesList = new ArrayList<Date>();
		datesList.add(date);
		datesList.add(date2);
		model.addAttribute("datesList",datesList);
		return "/course/dates";
	}
	
	@RequestMapping("/numbers")
	public String numbers(Model model) throws ParseException{
		List<Integer> numList = new ArrayList<Integer>();
		numList.add(1);
		numList.add(12);
		numList.add(13);
		model.addAttribute("numList",numList);
		return "/course/numbers";
	}
	
	@RequestMapping("/strings")
	public String strings(Model model){
		Object object = "123";
		model.addAttribute("object",object);
		
		List<Integer> numList = new ArrayList<Integer>();
		numList.add(1);
		numList.add(12);
		numList.add(13);
		model.addAttribute("numList",numList);
		
		String name = null;
		model.addAttribute("name",name);
		
		String[] nameArr = {null,"1",null};
		model.addAttribute("textArray",nameArr);
		
		List<String> nameList = new ArrayList<String>();
		nameList.add("1");
		nameList.add(null);
		
		model.addAttribute("nameList",nameList);
		
		Set<String> nameSet = new HashSet<String>();
		nameSet.add(null);
		nameSet.add("1");
		
		model.addAttribute("nameSet",nameSet);
		
		
		String text = null;
		model.addAttribute("text",text);
		
		String[] textArr = {"abc"};
		model.addAttribute("textArry",textArr);
		
		List<String> textList = new ArrayList<String>();
		textList.add("abc");
		textList.add(null);
		model.addAttribute("textList",textList);
		
		Set<String> textSet = new HashSet<String>();
		textSet.add("abc");
		textSet.add("");
		textSet.add("default");
		model.addAttribute("textSet",textSet);
		
		String[]  namesArray =  {"a","b","c"};
		model.addAttribute("namesArray",namesArray);
		
		List<String> namesList = new ArrayList<String>();
		namesList.add("a");
		namesList.add("b");
		namesList.add("c");
		model.addAttribute("namesList",namesList);
		
		Set<String> namesSet = new HashSet<String>();
		namesSet.add("a");
		namesSet.add("b");
		namesSet.add("c");
		model.addAttribute("namesSet",namesSet);
		
		return "/course/strings";
	}
	@RequestMapping("/objects")
	public String objects(Model model){
		Object obj = null;
		model.addAttribute("obj",obj);
		
		Object[] objArray = {"abc"};
		model.addAttribute("objArray",objArray);
		
		List<String> objList = new ArrayList();
		objList.add("1");
		objList.add(null);
		objList.add("2");
		model.addAttribute("objList",objList);
		
		Set<String> objSet = new HashSet();
		objSet.add("1");
		objSet.add(null);
		objSet.add("2");
		model.addAttribute("objSet",objSet);
		
		return "/course/objects";
	}
	@RequestMapping("/bools")
	public String bools(Model model){
		return "/course/bools";
	}
	
	@RequestMapping("/arrays")
	public String arrays(Model model){
		List<String> object = new ArrayList<String>();
		object.add("1");
		object.add("2");
		model.addAttribute("object",object);
		
		Integer[] array = {1,2,3};
		model.addAttribute("array",array);
		Integer[] array2 = {1,3};
		model.addAttribute("array2",array2);
		return "/course/arrays";
	}
	
	@RequestMapping("/lists")
	public String lists(Model model){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(2);
		model.addAttribute("list",list);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(2);
		model.addAttribute("list2",list2);
		return "/course/lists";
	}
	
	@RequestMapping("/sets")
	public String sets(Model model){
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		model.addAttribute("set",set);
		
		Integer[] elements = {1,2};
		model.addAttribute("elements",elements);
		return "/course/sets";
	}
	@RequestMapping("/maps")
	public String maps(Model model){
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("1",1);
		map.put("2",2);
		map.put("3",3);
		model.addAttribute("map",map);
		
		String[] keys = {"1","2"};
		model.addAttribute("keys",keys);
		
		Integer[] values = {1,2};
		model.addAttribute("values",values);
		
		return "/course/maps";
	}
	
	@RequestMapping("/aggregates")
	public String aggregates(Model model){
		Integer[] array = {1,2,3,4};
		model.addAttribute("array",array);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		model.addAttribute("list",list);
		return "/course/aggregates";
	}
	
	@RequestMapping("/ids")
	public String ids(Model model){
		return "/course/ids";
	}
	
	@RequestMapping("/threeElementOperation")
	public String threeElementOperation(Model model){
		model.addAttribute("name", "ljk");
		return "/course/threeElementOperation";
	}
	
	@RequestMapping("/elvis")
	public String elvis(Model model){
		model.addAttribute("age", null);
		model.addAttribute("age2", 18);
		return "/course/elvis";
	}
	
	@RequestMapping("/noOperation")
	public String noOperation(Model model){
		model.addAttribute("name", null);
		return "/course/noOperation";
	}
	
	@RequestMapping("/thhref")
	public String thhref(Model model){
		return "/course/thhref";
	}
	
	@RequestMapping("/thclass")
	public String thclass(Model model){
		return "/course/thclass";
	}
	
	@RequestMapping("/thattr")
	public String thattr(Model model){
		return "/course/thattr";
	}
	
	@RequestMapping("/thvalue")
	public String thvalue(Model model){
		model.addAttribute("name", "ljk");
		return "/course/thvalue";
	}
	
	@RequestMapping("/thaction")
	public String thaction(Model model){
		return "/course/thaction";
	}
	
	@RequestMapping("/thid")
	public String thid(Model model){
		model.addAttribute("id", 123);
		return "/course/thid";
	}
	
	@RequestMapping("/thonclick")
	public String honclick(Model model){
		return "/course/thonclick";
	}
	
	@RequestMapping("/thselected")
	public String thselected(Model model){
		model.addAttribute("sex", 1);
		return "/course/thselected";
	}
	
	@RequestMapping("/thsrc")
	public String thsrc(Model model){
		return "/course/thsrc";
	}
	
	@RequestMapping("/thstyle")
	public String thstyle(Model model){
		model.addAttribute("isShow", true);
		return "/course/thstyle";
	}
	
	@RequestMapping("/thwith")
	public String thwith(Model model){
		model.addAttribute("today", new Date());
		
		List<User> users = new ArrayList<User>();
		users.add(new User("ljk",18));
		users.add(new User("ljk2",18));
		model.addAttribute("users",users);
		return "/course/thwith";
	}
	
	
}
