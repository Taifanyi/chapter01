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
	
	public String cal(String text, boolean isPercent) throws Exception {
		double secondResult = secondNum == null ? Double.valueOf(text).doubleValue() : Double.valueOf(secondNum).doubleValue();
		if (secondResult == 0 && this.lastOp.equals("/")) {
			return "0";
		}
		if (isPercent) {
			secondResult = MyMath.multiply(Double.valueOf(firstNum), MyMath.divide(secondResult, 100));
		}
		if (this.lastOp.equals("+")) {
			firstNum = String.valueOf(MyMath.add(Double.valueOf(firstNum), secondResult));
		} else if (this.lastOp.equals("*")) {
			firstNum = String.valueOf(MyMath.multiply(Double.valueOf(firstNum), secondResult));
		} else if (this.lastOp.equals("/")) {
			firstNum = String.valueOf(MyMath.divide(Double.valueOf(firstNum), secondResult));
		}
		secondNum = secondNum == null ? text : secondNum;
		this.isSecondNum = true;
		return firstNum;
	}
	
	public String setReciprocal(String text) {
		if (text.equals("0")) {
			return text;
		} else {
			this.isSecondNum = true;
			return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
		}
	}
	
	public String setOp(String cmd, String text) {
		this.lastOp = cmd;
		this.secondNum = null;
		this.isSecondNum = true;
		return null;
	}
	
	public String setNegative(String text) {
		if (text.indexOf("-") == 0) {
			return text.substring(1, text.length());
		}
		return text.equals("0") ? text : "-" + text;
	}
	
	public String catNum(String cmd, String text) {
		String result = cmd;
		if (!text.equals("0")) {
			if (isSecondNum) {
				isSecondNum = false;
			} else {
				result = text + cmd;
			}
		}
		if (result.indexOf(".") == 0) {
			result = "0" + result;
		}
		return result;
	}
	
	
	
	
	
	
	
}
