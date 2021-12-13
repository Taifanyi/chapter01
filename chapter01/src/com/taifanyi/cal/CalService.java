package com.taifanyi.cal;

import java.math.BigDecimal;

public class CalService {
	private double store = 0;
	private String firstNum = null;
	private String lastOp = null;
	private String secondNum = null;
	private boolean isSecondNum = false;
	
	private String numString = "0123456789.";
	private String opString = "+-*/";
	
	public CalService() {
		super();
	}
	
	public String callMethond(String cmd, String text) throws Exception {
		if(cmd.equals("C")) {
			return clearAll();
		} else if (cmd.equals("CE")) {
			return clear(text);
		} else if (cmd.equals("Back")) {
			return backSpace(text);
		} else if (numString.indexOf(cmd)!=-1) {
			return catNum(cmd, text);
		} else if (opString.indexOf(cmd)!=-1) {
			return setOp(cmd, text);
		} else if (cmd.equals("=")) {
			return cal(text, false);
		} else if (cmd.equals("+/-")) {
			return setNegative(text);
		} else if (cmd.equals("sqrt")) {
			return setReciprocal(text);
		} else if (cmd.equals("%")) {
			return cal(text, true);
		} else {
			return mCmd(cmd, text);
		}
	}
	
	
	
	
	
	
}
