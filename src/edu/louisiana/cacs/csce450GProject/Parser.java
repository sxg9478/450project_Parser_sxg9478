package edu.louisiana.cacs.csce450Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;


public class Parser{
	
    public String fileName="";
	private ParseTokens parsetokens=null;
	private String parsetree=null;
	private TreeDisplay treedisplay=null;
	public String[] specialSymbols={"+","*"};
	public Parser(String fileName){
		this.fileName=fileName;
		System.out.println("File to parse : "+fileName);
		ArrayList<String> datafromFile=readFromFile();
		for(int i=0;i<datafromFile.size();i++)
		{
		parsetokens=new ParseTokens();
		parsetokens.setExpression(datafromFile.get(i).trim().replaceAll("\\s", ""));
		parsetree=parsetokens.parser();
		//parse();
		}
	}
	
	public ArrayList<String> readFromFile()
	{
		BufferedReader br=null;
		ArrayList<String> filedata=new ArrayList<String>();
		String stline=null;
		try
		{
			br=new BufferedReader(new FileReader(fileName));
			while((stline=br.readLine())!=null)
			{
				filedata.add(stline);
			}
		}
		catch (Exception e) {
		  e.printStackTrace();
		}
		
		return filedata;
		
	}
	
	
	
	
	
	
	public void printParseTree(){
		
		treedisplay=new TreeDisplay();
		treedisplay.setparsetree(parsetree);
		treedisplay.setspaecialSymbol(specialSymbols);
		treedisplay.display();
		System.out.println("Hello World from " + getClass().getName());
	}

	
	public void parse(){
		printParseTree();
	}
}
class Counter {
	private int count=0;
	public void addcount()
	{
		count++;
	}

	public int getCount()
	{
		return count;
	}
}
class OutPutTable {

	private String stack=" ";
	private String inputtokens=" ";
	private String actionlookup=" ";
	private String actionvalue=" ";
	private String valueofLHS=" ";
	private String lengthofRHS=" ";
	private String tempstack=" ";
	private String gotolookup=" ";
	private String gootovalue=" ";
	private String stackaction=" ";
	private String parsetreestack=" ";
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public String getInputtokens() {
		return inputtokens;
	}
	public void setInputtokens(String inputtokens) {
		this.inputtokens = inputtokens;
	}
	public String getActionlookup() {
		return actionlookup;
	}
	public void setActionlookup(String actionlookup) {
		this.actionlookup = actionlookup;
	}
	public String getActionvalue() {
		return actionvalue;
	}
	public void setActionvalue(String actionvalue) {
		this.actionvalue = actionvalue;
	}
	public String getValueofLHS() {
		return valueofLHS;
	}
	public void setValueofLHS(String valueofLHS) {
		this.valueofLHS = valueofLHS;
	}
	public String getLengthofRHS() {
		return lengthofRHS;
	}
	public void setLengthofRHS(String lengthofRHS) {
		this.lengthofRHS = lengthofRHS;
	}
	public String getTempstack() {
		return tempstack;
	}
	public void setTempstack(String tempstack) {
		this.tempstack = tempstack;
	}
	public String getGotolookup() {
		return gotolookup;
	}
	public void setGotolookup(String gotolookup) {
		this.gotolookup = gotolookup;
	}
	public String getGootovalue() {
		return gootovalue;
	}
	public void setGootovalue(String gootovalue) {
		this.gootovalue = gootovalue;
	}
	public String getStackaction() {
		return stackaction;
	}
	public void setStackaction(String stackaction) {
		this.stackaction = stackaction;
	}
	public String getParsetreestack() {
		return parsetreestack;
	}
	public void setParsetreestack(String parsetreestack) {
		this.parsetreestack = parsetreestack;
	}


	public void displaytableInfo(int stackno)
	{

		System.out.print((stackno+1)+"\t");
		System.out.format("%20s",getStack());
		System.out.format("%20s",getInputtokens());
		System.out.format("%20s",getActionlookup());
		System.out.format("%20s",getActionvalue());
		if(getValueofLHS()!=null)
			System.out.format("%20s",getValueofLHS());
		System.out.format("%20s",getLengthofRHS());
		System.out.format("%20s",getTempstack());
		System.out.format("%20s",getGotolookup());
		System.out.format("%20s",getGootovalue());
		System.out.format("%40s",getStackaction());
		System.out.format("%40s",getParsetreestack());
		System.out.println("");	
	}

}
class ParserTreeRules {


	private String data=null;
	private String parsetree=null;

	private String pushdataValue=null;
	private String symbol=null;
	private Pusher pusher=null;

	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}
	public String getParsetree() {
		return parsetree;
	}

	public void setParsetree(String parsetree) {
		this.parsetree = parsetree;
	}
	public String getPushdata() {
		return pushdataValue;
	}
	public void setPushdata(String pushdata) {
		this.pushdataValue = pushdata;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}public Pusher getPusher() {
		return pusher;
	}
	public void setPusher(Pusher pusher) {
		this.pusher = pusher;
	}
	public String  parseTreegenrate()
	{

		String returndata="";
		parsetree=parsetree.replace(" ", "");
		String[] rulespliter=data.split("\\"+symbol);
		String parstreeTemp=null;
		Vector<String> dataPrepareForRules=new Vector<String>();
		for(int i=0;i<rulespliter.length;i++)
		{
			parstreeTemp=parsetree.substring(parsetree.indexOf("["+rulespliter[i]),parsetree.length());
			parstreeTemp=parstreeTemp.substring(0,parstreeTemp.indexOf("]")+1);
			parsetree =parsetree.substring(0,parsetree.indexOf("["+rulespliter[i]))+parsetree.substring(parsetree.indexOf("["+rulespliter[i])+parstreeTemp.length(),parsetree.length());
			if(i==0)
			{
				parstreeTemp="["+pushdataValue+parstreeTemp+"]";
			}
			dataPrepareForRules.add(parstreeTemp);
		}
		for(int i=0;i<dataPrepareForRules.size();i++)
		{
			returndata+=dataPrepareForRules.get(i);
			if(i<dataPrepareForRules.size()-1)
				returndata+=symbol;
		}
		returndata+=parsetree;
		return returndata;
	}

}
class ParseRules {

	private String currentpush = null;
	private String parsedString = null;
	private OutPutTable outputdata = null;
	private String pushdata = null;
	private String stack = "";
	private RulesInformation rinfo = null;
	private Pusher pusher = null;
	private UserTable usertable = null;
	private String tempstack = null;
	private String parseTreeStack = "";
	private String returnvalue = "";
	private String keyvalue = "id";
	private String[] symbols = {"+","*"};
	private Counter counter = null;
	private ParserTreeRules parsetreeRules=null;

	public ParseRules(UserTable usertable) {
		rinfo = new RulesInformation();
		pusher = new Pusher();
		this.usertable = usertable;
		counter = new Counter();
		parsetreeRules=new ParserTreeRules();

	}


	public Pusher getpusher()
	{
		return pusher;
	}


	public String parse_scase(String indexinfo,
			Hashtable<String, String> userinfo, String stack, String equation) {

		this.stack = stack;
		this.tempstack = stack;
		outputdata = new OutPutTable();
		parsedString = userinfo.get("parsed");

		currentpush = getPushdata(parsedString, indexinfo);
		outputdata.setStack("" + stack);
		outputdata.setInputtokens("" + equation);
		outputdata.setActionvalue("" + indexinfo);
		outputdata.setStackaction(currentpush);
		outputdata.setActionlookup(gelookupAction(stack, parsedString));

		if(parsedString.equals(keyvalue))
			parseTreeStack=parsedString+parseTreeStack;
		outputdata.setParsetreestack(parseTreeStack);
		outputdata.displaytableInfo(counter.getCount());


		pusher.setParseTree(parseTreeStack);
		counter.addcount();
		return returnvalue;

	}

	public String parse_rcase(String indexinfo,
			Hashtable<String, String> userinfo, String stack, String equation) {

		// System.out.println("asdasd"+stack);

		this.stack = stack;
		this.tempstack = stack;

		// System.out.println(tempstack);
		outputdata = new OutPutTable();
		parsedString = userinfo.get("parsed");
		String lhsvalue = getValueLHS(indexinfo);
		outputdata.setStack("" + stack);
		outputdata.setInputtokens("" + equation);
		outputdata.setActionvalue("" + indexinfo);
		outputdata.setActionlookup(gelookupRAction(stack, parsedString));
		outputdata.setValueofLHS(lhsvalue);
		int length = getLengthofRHS(indexinfo);
		outputdata.setLengthofRHS("" + length);
		// outputdata.displaytableInfo(2);
		String tempstackinfo = gettempStack(length);
		outputdata.setTempstack(tempstackinfo);
		outputdata.setGotolookup(gotoLookup(tempstackinfo, lhsvalue));

		// outputdata.displaytableInfo(2);

		outputdata.setGootovalue(gotoLookupValue(tempstackinfo, lhsvalue,
				getLengthofRHS(indexinfo)));

		// outputdata.displaytableInfo(2);

		currentpush = getPushdata(
				lhsvalue,
				gotoLookupValue(tempstackinfo, lhsvalue,
						getLengthofRHS(indexinfo)));
		outputdata.setStackaction(currentpush);
		outputdata.setParsetreestack(parseTreeStack);
		outputdata.displaytableInfo(counter.getCount());
		counter.addcount();
		return returnvalue;

	}

	public String gettempStack(int length) {
		// System.out.println(tempstack);
		String temp = "";
		if (length > 2) {
			temp = tempstack.substring(tempstack.length() - (length * 2 - 2),
					tempstack.length());
		}
		stack = stack.replace(temp, "");
		// System.out.println(tempstack.replace(temp,""));
		return tempstack.replace(temp, "");
	}

	public String gotoLookupValue(String index, String key, int length) {

		index = index.substring(index.length() - 1, index.length());
		return usertable.getDataAtPos(index, key);

	}

	public int getLengthofRHS(String indexinfo) {

		HashMap<String, String> tempdata = rinfo.getMapRules().get(indexinfo);
		return rinfo.getMapRulesKeylength(tempdata);
	}

	public String gotoLookup(String index, String key) {
		index = index.substring(index.length() - 1, index.length());
		return "[" + index + "," + key + "]";

	}

	public String getValueLHS(String indexinfo) {

		tempstack = tempstack.replace(pusher.getPrevoiusPush(), "");
		stack = stack.replace(pusher.getPrevoiusPush(), "");
		HashMap<String, String> tempdata = rinfo.getMapRules().get(indexinfo);
		String tempkeydata = "";
		String key = rinfo.getMapRulesKey(tempdata);

		if (key.equals(keyvalue)) {

			if(!getSymbol(rinfo.getMapRulesKey(tempdata)).equals("-1"))
			{
				tempkeydata = "["+rinfo.getMapRulesKeyValue(tempdata).trim()+ ","
				+ rinfo.getMapRulesKey(tempdata) + "]"+rinfo.getMapRulesKey(tempdata);
			}
			else
				tempkeydata = "["+rinfo.getMapRulesKeyValue(tempdata).trim() + ","
				+ rinfo.getMapRulesKey(tempdata)+"]";

		} else {
			tempkeydata = rinfo.getMapRulesKeyValue(tempdata);
		}
		if (pusher.getparseTree().size() == 0) {
			parseTreeStack =  tempkeydata;
			pusher.pushparseetree(parseTreeStack);

		} else {

			if(!getSymbol(rinfo.getMapRulesKey(tempdata)).equals("-1"))
			{



				parsetreeRules.setData(rinfo.getMapRulesKey(tempdata));
				parsetreeRules.setParsetree(parseTreeStack);
				parsetreeRules.setPushdata(tempkeydata);
				parsetreeRules.setSymbol(getSymbol(rinfo.getMapRulesKey(tempdata)));
				parsetreeRules.setPusher(pusher);
				parseTreeStack=parsetreeRules.parseTreegenrate();
				pusher.setValue(pushdata);
				pusher.pushparseetree(parseTreeStack);


			}
			else
			{
				if (!key.equals(keyvalue)) 
					parseTreeStack = "[ " + tempkeydata + " "
					+ pusher.getPrevoiusPushparestree() + "]";
				else
					parseTreeStack =  tempkeydata + " "
					+ pusher.getPrevoiusPushparestree() ;
				pusher.pushparseetree(parseTreeStack);
			}

		}
		pusher.setParseTree(parseTreeStack);
		return rinfo.getMapRulesKeyValue(tempdata);
	}

	public String getstackadder(String stack) {
		return stack;
	}

	public String getPushdata(String data, String pusherdata) {
		pusher.push(data);
		pushdata = "push" + data + pusherdata.replace("R", "").replace("S", "");
		returnvalue = pusherdata.replace("R", "").replace("S", "");
		pusher.pushValue(returnvalue);
		return pushdata;
	}

	public String gelookupAction(String stackid, String parsedString) {
		stackid = stackid.substring(stackid.length() - 1, stackid.length());
		tempstack = tempstack.replace(stackid, "");
		return "[" + stackid + "," + parsedString + "]";

	}

	public String gelookupRAction(String stackid, String parsedString) {
		stackid = stackid.substring(stackid.length() - 1, stackid.length());
		stackid = "" + pusher.getPrevoiusPushValue();
		tempstack = tempstack.replace(stackid, "");
		stack = stack.replace(stackid, "");
		return "[" + stackid + "," + parsedString + "]";

	}

	public String getCurrentPush() {
		return currentpush;
	}

	public String getStack() {
		return stack + pushdata.replace("push", "");
	}

	public String getSymbol(String data)
	{
		String symbol="-1";
		for(int i=0;i<symbols.length;i++)
			if(data.contains(symbols[i]))
			{
				symbol=symbols[i];
				break;
			}
		//System.out.println(symbol);
		return symbol;
	}
}
class ParseTokens {

	private String equation=null;
	private String[] parsetokens={"id","+","*","(",")","$"};
	private Hashtable<String, String> paresdinfo=null;
	private String parse=null;
	private String unparse=null;
	private String endChar="$";
	private ParseRules parserules=null;
	private UserTable usertable=null;
	private String stacklastindex="0";
	private String initstack="0";
	public ParseTokens()
	{
		paresdinfo=new Hashtable<String, String>();
		usertable=new UserTable();
		parserules=new ParseRules(usertable);
		
	}

	public void setExpression(String equation)
	{
		this.equation=equation;
	}

	public String parser()
	{

		Hashtable<String, String> info=getparserStringInfo(equation);
		String userinfo="";
		userinfo=usertable.getDataAtPos(stacklastindex, info.get("parsed"));
		int count=0;
		while(!userinfo.equals("accept"))
		{
			userinfo=usertable.getDataAtPos(stacklastindex, info.get("parsed"));
			if(userinfo.startsWith("R"))
			{	
				stacklastindex=parserules.parse_rcase(userinfo,info,initstack,equation);
			}
			else
				stacklastindex=parserules.parse_scase(userinfo,info,initstack,equation);
			initstack=parserules.getStack();
			count++;
			if(!userinfo.contains("R"))	
			{
				equation=info.get("unparsed");
				info=getparserStringInfo(equation);
			}

		}

		String parsetree=parserules.getpusher().getParseTree();
		System.out.println(parsetree);
		return parsetree;
	}

	public Hashtable<String,String> getparserStringInfo(String data)
	{
		int index=0;
		int temp=0;
		String availbleString="";
		for(int i=0;i<parsetokens.length;i++)
		{
			if(data.contains(parsetokens[i]))
			{
				index=data.indexOf(parsetokens[i]);
				if(temp>=index)
				{
					availbleString=parsetokens[i];
					break;
				}
				temp=index;
			}
		}
		parse=data.substring(0,availbleString.length());
		unparse=data.substring(availbleString.length(),data.length());
		paresdinfo.put("parsed", parse);
		paresdinfo.put("unparsed", unparse);
		return paresdinfo;
	}
}
class RulesInformation {

	private HashMap<String,String> equationsdata=null;
	private TreeMap<String,HashMap<String,String>> maprules=null;
	public RulesInformation()
	{
		equationsdata=new HashMap<String, String>();
		maprules=new TreeMap<String, HashMap<String,String>>();
		storeEquations();
		mapRules();

	}


	public HashMap<String, String> storeEquations()
	{
		equationsdata.put("E+T", "E");
		equationsdata.put("T", "E");
		equationsdata.put("T*F", "T");
		equationsdata.put("F", "T");
		equationsdata.put("(E)", "F");
		equationsdata.put("id", "F");
		return equationsdata;
	}

	public HashMap<String, String> getstoreEquations()
	{
		return equationsdata;
	}

	public void mapRules()
	{

		storedata("R1","E+T", "E");
		storedata("R2","T", "E");
		storedata("R3","T*F", "T");
		storedata("R4","F", "T");
		storedata("R5","(E)", "F");
		storedata("R6","id", "F");
		/*HashMap<String, String> storeequations=getstoreEquations();
		Iterator<String> it = storeequations.keySet().iterator();
		String key="";
		int count=1;
		while (it.hasNext()) {
			key=it.next();
			storedata("R"+count,key, storeequations.get(key));
			it.remove();
			count++;
		}*/
	}

	public void storedata(String rulekey,String key,String value)
	{
		HashMap<String,String> rules=new HashMap<String, String>();
		rules.put(key, value);
		maprules.put(rulekey, rules);
		rules=null;
	}


	public TreeMap<String, HashMap<String,String>> getMapRules()
	{
		return maprules;
	}

	public String getMapRulesKeyValue(HashMap<String,String> mapdata)
	{
		Iterator<String> it=mapdata.keySet().iterator();
		String key="";
		String value="";
		while(it.hasNext())
		{
			key=it.next();
			value=mapdata.get(key);
		}
		return value;
	}

	public String getMapRulesKey(HashMap<String,String> mapdata)
	{
		Iterator<String> it=mapdata.keySet().iterator();
		String key="";
		String value="";
		while(it.hasNext())
		{
			key=it.next();

		}
		return key;
	}


	public int getMapRulesKeylength(HashMap<String,String> mapdata)
	{
		Iterator<String> it=mapdata.keySet().iterator();
		String key="";
		while(it.hasNext())
		{
			key=it.next();

		}
		if(key.equals("id"))
			key="i";

		return key.length();
	}
}


class Pusher {

	private Vector<String> pusher=null;
	private Vector<String> parsetreepusher=null;
	private Vector<String> pushervalue=null;
	private String parsetree=null;

	private String setvalue=null;

	public Pusher()
	{
		pusher=new Vector<String>();
		parsetreepusher=new Vector<String>();
		pushervalue=new Vector<String>();
	}

	public void push(String key)
	{
		pusher.add(key);
	}

	public String getPrevoiusPush()
	{
		return pusher.get(pusher.size()-1);
	}

	public void pushValue(String key)
	{
		pushervalue.add(key);
	}

	public String getPrevoiusPushValue()
	{

		return pushervalue.get(pushervalue.size()-1);
	}

	public Vector<String> getPushValue()
	{
		return pushervalue;
	}


	public void pushparseetree(String key)
	{
		parsetreepusher.add(key);
	}

	public String getPrevoiusPushparestree()
	{
		return parsetreepusher.get(parsetreepusher.size()-1);
	}

	public Vector<String> getparseTree()
	{
		return parsetreepusher;
	}

	public void setValue(String setvalue)
	{
		this.setvalue=setvalue;
	}


	public String getValue()
	{
		return setvalue;
	}


	public void setParseTree(String parsetree)
	{
		this.parsetree=parsetree;
	}

	public String getParseTree()
	{
		return parsetree;
	}
}

class TreeDisplay {

	
	private String parseTree="";
	private String[] specialSymbols=null;
	Vector<String> splittreedata=null;
	public ArrayList<String> specialcharsList=new ArrayList<String>();
	public void setparsetree(String parsetree)
	{
		this.parseTree=parsetree;
	}
	public void setspaecialSymbol(String[] symbols)
	{
		this.specialSymbols=symbols;
		parseTreechars(parseTree);
	}
	
	public void parseTreechars(String parsetree)
	{
		for(int i=0;i<specialSymbols.length;i++)
		{
			if(parsetree.contains(specialSymbols[i]))
			{
				specialcharsList.add(specialSymbols[i]);
			}
		}
	}
	

	public void display()
	{
	
		splittreedata=new Vector<String>();
		String[] parser=null;
		for(int i=0;i<specialcharsList.size();i++)
		{
			parser=parseTree.split("\\"+specialcharsList.get(i));
			if(parser[0].length()>0)
			{
				splittreedata.add(parser[0]);
				parseTree=specialcharsList.get(i)+parser[1];
			}
		}
		splittreedata.add(parseTree);
		displayinTree();
	}

	

	public void displayinTree()
	{
		int spaceview=0;
		String[] spliter=null;
		String data=null;
		String spacer="";
		String spackdisplay="";
		for(int i=0;i<splittreedata.size();i++)
		{
			spackdisplay="";
			for(int space=0;space<=spaceview;space++)
			{
				spacer+=" ";
			}
			int start=countChar(splittreedata.get(i), '[');
			int end=countChar(splittreedata.get(i), ']');
			spaceview+=(end-start);
			data=replace(splittreedata.get(i));
			spliter=data.split(",");
			for(int keys=0;keys<spliter.length;keys++)
			{
				if(keys==0)
					System.out.println(spacer+spliter[keys]);
				else
				{
					spackdisplay+=spacer;
					System.out.println(spackdisplay+spliter[keys]);
					spackdisplay+=" ";
				}
			}
		}
	}


	public String replace(String data)
	{
		data=data.replace("[", ",").replace("]","");
		return data;
	}


	public int countChar(String data,char charcter)
	{
		int count=0;
		for(int i=0;i<data.length();i++)
		{
			if(data.charAt(i)==charcter)
				count++;
		}
		return count;
	}
}
class UserTable {

	public String[] hedinginfo={"id","+","*","(",")","$","E","T","F"};
	private Vector<String[]> rowsinfo=null;
	public UserTable()
	{
		rowsinfo=new Vector<String[]>();
		storeRowInformation();
	}


	public void storeRowInformation()
	{

		String[] row1={"S5","","","S4","","","1","2","3"};
		rowsinfo.add(row1);
		String[] row2={"","S6","","","","accept","","",""};
		rowsinfo.add(row2);
		String[] row3={"","R2","S7","","R2","R2","","",""};
		rowsinfo.add(row3);
		String[] row4={"","R4","R4","","R4","R4","","",""};
		rowsinfo.add(row4);
		String[] row5={"S5","","","S4","","","8","2","3"};
		rowsinfo.add(row5);
		String[] row6={"","R6","R6","","R6","R6","","",""};
		rowsinfo.add(row6);
		String[] row7={"S5","","","S4","","","","9","3"};
		rowsinfo.add(row7);
		String[] row8={"S5","","","S4","","","","","10"};
		rowsinfo.add(row8);
		String[] row9={"","S6","","","s11","","","",""};
		rowsinfo.add(row9);
		String[] row10={"","R1","S7","","R1","R1","","",""};
		rowsinfo.add(row10);
		String[] row11={"","R3","R3","","R3","R3","","",""};
		rowsinfo.add(row11);
		String[] row12={"","R5","R5","","R5","R5","","",""};
		rowsinfo.add(row12);
	}

	public Vector<String[]> getUserInfo()
	{
		return rowsinfo;
	}


	public int getheaderkeyIndex(String key)
	{
		for(int i=0;i<hedinginfo.length;i++)
		{
			if(key.equals(hedinginfo[i]))
			{
				return i;
			}
		}
		return -1;
	}

	public String getDataAtPos(String id,String key)
	{
		int index=getheaderkeyIndex(key);
		String value="";
		value=getUserInfo().get(Integer.parseInt(id))[index];
		return value;                           
	}
}


