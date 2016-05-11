class reservation{
	
	private int r_id;//key reservation id
	private int p_id;//Combination of movie and a hall
	private String f_name;//His/her name
	private String l_name;//His/her last name
	private int phone;//His phone
	private int no_seats;//number of seats
	
	//CONSTRUCTOR
	public reservation(){}
	
	public reservation(int r_id, int p_id, String f_name, String l_name, int phone,int no_seats){
		this.r_id = r_id;
		this.p_id = p_id;
		this.f_name = f_name;	
		this.l_name = l_name;
		this.phone = phone;
		this.no_seats = no_seats;
	}	
	
	public reservation(int p_id, String f_name, String l_name, int phone,int no_seats){
		this.p_id = p_id;
		this.f_name = f_name;	
		this.l_name = l_name;
		this.phone = phone;
		this.no_seats = no_seats;
	}	
	
	//GET
	public int getR_id(){return r_id;}
	public int getP_id(){return p_id;}
	public String getF_name(){return f_name;}
	public String getL_name(){return l_name;}
	public int getPhone(){return phone;}
	public int getNo_seats(){return no_seats;}
	//SET
	public void setR_id(int o){r_id=o;}
	public void setP_id(int o){p_id=o;}
	public void setF_name(String o){f_name=o;}
	public void setL_name(String o){l_name=o;}
	public void setPhone(int o){phone=o;}
	public void setNo_seats(int o){no_seats=o;}
}