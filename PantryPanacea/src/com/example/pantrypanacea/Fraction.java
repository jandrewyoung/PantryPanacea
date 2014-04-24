package com.example.pantrypanacea;

public class Fraction {
	private int numer, denom;
	private String fraction;
	double value;
	
	public Fraction(){
		numer = 0;
		denom =1;
		value = 0;
		fraction = "0/1";
	}
	
	public Fraction(int n, int d){
		numer = n;
		denom = d;
		value = n/d;
		if(denom == 1)
			fraction = Integer.toString(numer);
		else
			fraction = Integer.toString(numer)+"/"+Integer.toString(denom);
	}
	
	public Fraction(int n){
		numer = n;
		denom = 1;
		value = numer/denom;
		fraction = Integer.toString(numer);
	}
	
	public Fraction(String string){
		if(string.contains("/")){
			numer = Integer.parseInt(string.split("/")[0]);
			denom = Integer.parseInt(string.split("/")[1]);
			value = numer/denom;
			fraction = string;
		}
		else{
			numer = Integer.parseInt(string);
			denom = 1;
			value = numer;
			fraction = Integer.toString(numer);
		}
	}
	public void setNumer(int n){
		numer = n;
		value = n/denom;
		fraction = Integer.toString(n)+"/"+Integer.toString(denom);
	}
	
	public void setDenom(int d){
		denom = d;
		value = numer/d;
		fraction = Integer.toString(numer)+"/"+Integer.toString(d);
	}
	
	public int getNumer(){
		return numer;
	}
	
	public int getDenom(){
		return denom;
	}
	
	public String getString(){
		return fraction;
	}
	
	public double getValue(){
		return value;
	}
	
	public Fraction add(Fraction b){
		int common = lcd(denom, b.denom);
		Fraction commonA = new Fraction();
		Fraction commonB = new Fraction();
		commonA = convert(common);
		commonB = b.convert(common);
		Fraction sum = new Fraction((commonA.numer+commonB.numer), common);		
		sum = sum.reduce();
		return sum;
	}
	
	public Fraction subtract(Fraction b){
		int common = lcd(denom, b.denom);
		Fraction commonA = new Fraction();
		Fraction commonB = new Fraction();
		commonA = convert(common);
		commonB = b.convert(common);
		Fraction diff = new Fraction((commonA.numer-commonB.numer), common);		
		diff = diff.reduce();
		return diff;	
		}
	
	public Fraction multiply(Fraction b){
		Fraction product = new Fraction((numer*b.numer), (denom*b.denom));
		return product;
	}
	
	public Fraction divide(Fraction b){
		Fraction quotient = new Fraction((numer*b.denom),(denom*b.numer));
		return quotient;
	}
	
	private int lcd(int d1, int d2){
		int factor = d1;
		while((d1%d2) != 0)
			d1 += factor;
		return d1;
	}
	
	private int gcd(int d1, int d2){
		int factor = d2;
		while(d2 != 0){
			factor = d2;
			d2 = d1%d2;
			d1 = factor;
		}
		return d1;
	}
	
	private Fraction convert(int common){
		int factor = common / denom;
		Fraction result = new Fraction((numer*factor), common);
		return result;
	}
	
	private Fraction reduce(){
		int common = 0;
		int n = Math.abs(numer);
		int d = Math.abs(denom);
		if(n > d)
			common = gcd(n,d);
		else if(n < d)
			common = gcd(d, n);
		else common = n;
		Fraction result = new Fraction((numer/common),(denom/common));
		return result;
	}
}
