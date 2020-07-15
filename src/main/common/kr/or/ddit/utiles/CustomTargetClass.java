package kr.or.ddit.utiles;

import java.text.SimpleDateFormat;
import java.util.Date;

//사용자 정의 태그 작성을위해 활용되는 자원
//1. 사용자 정의 태그는 특정 클래스의 메서드를 대상으로 작성하고 활용
//	  메서드 선언시 반환타입과 파라메터 타입은 반드시 WrapperClass 타입
//   int-> Integer
//2. 메서드의 접근 지정자는 반드시 public이고, static 선언과 동반되어야 함.

public class CustomTargetClass {
	
	public static Integer plus(Integer i, Integer j){
		return i + j;
	}
	
	public static Integer multiple(String i, Integer j){
		return Integer.parseInt(i) * j;
	}
	
	public static String today(){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD");
		return sdf.format(new Date()); 
	}
	
	public static Integer charCount(String target, String delim){
		int idx = 0;
		int cnt = 0;
		
		idx = target.indexOf(delim);
		for(; idx != -1; cnt++){
			target = target.substring(target.indexOf(delim)+1,target.length());
			idx = target.indexOf(delim);
		}
		return cnt;
	}
	
	
}
