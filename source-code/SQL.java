import java.sql.*;
import java.util.*;

class SQL{
	
	static String insertR = "INSERT INTO reservation (p_id,f_name,l_name,phone,no_seats) VALUES (?,?,?,?,?)";//insert into reservation
    static String deleteR = "DELETE FROM reservation R WHERE R.r_id=?";//delete a reservation
    static String viewR = "SELECT * FROM reservation WHERE r_id=?";//view all reservations
    static String viewAllM_H = "SELECT * FROM plays P";//view all movie ids and hall ids
	static String viewAll_M = "SELECT * FROM movie M WHERE M.m_id=?";//extract all movie fields using movie id
	static String viewAll_H = "SELECT * FROM hall H WHERE H.h_id=?";//extract all hall fields using hall id
	static String get_m_id = "SELECT M.m_id FROM movie M WHERE M.m_name=?";//gets m_id when m_name is given
	static String get_p_id = "SELECT P.p_id FROM plays P WHERE P.m_id=?";//gets p_id when m_id is given

	
	
	private int p_id = 0;//plays id
	private int m_id = 0;//movie id
	private int h_id = 0;//hall id
	private int h_seat_no = 0;//hall total seat number
	private int h_f_seat_no = 0;//hall free seats number
	private int r_seat_no = 0;//reserved seats number
	private String f_name = null;
	private String l_name = null;
	private int phone_no = 0;
	
	
	private String m_name = null;//movie name
	private String h_name = null;//hall name
	private String r_id = null;//reservation id (code)
	
	private String driverClassName = "sun.jdbc.odbc.JdbcOdbcDriver" ;
	private String url = "jdbc:odbc:"+"database";
	private String username = "";
	private String password = "";
	private ResultSet result = null;
	private Connection con = null;
	private Statement stmt = null;
	
	private PreparedStatement p_r_01 = null;
	private PreparedStatement p_r_02 = null;
	private PreparedStatement p_r_03 = null;
	private PreparedStatement p_r_04 = null;
	private PreparedStatement p_r_05 = null;
	
	private ResultSet r_p_s_01 = null;
	private ResultSet r_p_s_02 = null;
	private ResultSet r_p_s_03 = null;
	private ResultSet r_p_s_04 = null;
	private ResultSet r_p_s_05 = null;
	
	private String[][] temp = new String[movie_number()][4];//array
	
	
	public SQL(){}//default constructor
	

	public void open(){//opens connection to the database

			try{
				Class.forName(driverClassName);
			}
			catch(Exception e){
				System.out.println("Failed to load JDBC/ODBC driver.");
				return;
			}
		
			try{
				con=DriverManager.getConnection(url,username,password);
				stmt=con.createStatement();
			}
			catch(Exception e){
				System.out.println("problem connecting to "+url+".");
			}
	}//open() 
	
	
	public void close(){//closes connection to the database
		
		try{
			con.close();
		}
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}
	}//close()
	
	
	public void view_r(){//view all reservations
	
	}//view_r()
	

	

	
	public reservation findit(int o){
		
		open();//connection with the database
		//METABLHTES
		int temp_code = o;
		reservation return_r = null;
			
		try{
            PreparedStatement stmt = con.prepareStatement(viewR);
            stmt.setInt(1, temp_code);
 			ResultSet result = stmt.executeQuery();
			
			if (result.next()){

					return_r = new reservation(result.getInt("r_id"),result.getInt("p_id"),result.getString("f_name"),result.getString("l_name"),result.getInt("phone"),result.getInt("no_seats"));

			}			
		}//end of try
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());

		}//end of catch	
	
		close();//end connection
		return return_r;	
	}

	public void cancel_r(reservation o){//cancel a reservation
	
			//create values to be entry		
			p_id = o.getP_id();
			System.out.println(o.getR_id());
			
			open();//connection with the database

		
		//PREPARING TO UPDATE THE TABLE
		int temp_h_id2 = 0;
		int temp_h_seats2 = 0;
		
		String FOUND_HALL2 = "SELECT P.h_id FROM plays P WHERE P.p_id=?";
		String FOUND_SEATS2 = "SELECT H.h_f_seat_no FROM hall H WHERE h_id=?";
		String UPDATE_SEATS2 = "UPDATE hall H SET H.h_f_seat_no=? WHERE H.h_id=?";
		
		try{
			System.out.println("Brhskei to hall");
			PreparedStatement stmtF_H2 = con.prepareStatement(FOUND_HALL2);
			stmtF_H2.setInt(1, p_id);
		
			ResultSet resultF_H2 = stmtF_H2.executeQuery();
			if (resultF_H2.next()){
				temp_h_id2=resultF_H2.getInt("h_id");	
			}
			System.out.println(temp_h_id2);
			System.out.println("Brhskei tis theseis");
			PreparedStatement stmtF_S2 = con.prepareStatement(FOUND_SEATS2);
			stmtF_S2.setInt(1, temp_h_id2);
		
			ResultSet resultF_S2 = stmtF_S2.executeQuery();
			if (resultF_S2.next()){
				temp_h_seats2=resultF_S2.getInt("h_f_seat_no");	
			}
			
		//NOW UPDATING
		System.out.println(temp_h_seats2);
		System.out.println("Tha kanei update tis theseis");
			temp_h_seats2 = temp_h_seats2 + o.getNo_seats();

			PreparedStatement stmtU_S2 = con.prepareStatement(UPDATE_SEATS2);
			stmtU_S2.setInt(1, temp_h_seats2);
			stmtU_S2.setInt(2, temp_h_id2);
		
			stmtU_S2.executeQuery();
		}
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//end of catch	
        try{ 
            // delete it
            System.out.println("kanei delete");
            PreparedStatement stmtDEL2 = con.prepareStatement(deleteR);
            stmtDEL2.setInt(1, o.getR_id());

            stmtDEL2.executeQuery();
					
		}//end of try
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());

		}//end of catch	
	System.out.println("CANCEL OUT");
		close();//end connection
		
	}//cancel_r()
	
	public String from_p_id_to_hall(int o){//find hall from p_id
		open();
		System.out.println("from p to hall IN");
		String find_hall = "SELECT M.m_name FROM movie M WHERE M.m_id IN (SELECT P.m_id FROM plays P WHERE P.p_id="+o+")";//find the halls
		String rs="";
		
		try{
			
			PreparedStatement statement = con.prepareStatement(find_hall);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				rs+=result.getString("m_name");	
			}
			
			}//end of try
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//end catch

		close();
		System.out.println("from p to hall OUT");
		return rs;
	}//end finding

	
	
	public String[][] view_m_h(){//view all movie names and coresponding halls
				
				open();//open connection
				
			 try{
			 	
				result = stmt.executeQuery(viewAllM_H);//executes query viewAllM_H
				
					int i=0;

					p_r_01 = con.prepareStatement(viewAll_M);
					p_r_02 = con.prepareStatement(viewAll_H);
					
					String a;//for temp reasons
					String b;//for temp reasons
					int temp_int = 0;
					int temp_int2 = 0;
					while(result.next())//an uparxoun grammes sto apotelesma
			        {	
						a="";
			            b="";
			            p_id = result.getInt("p_id");
			   			m_id = result.getInt("m_id");
			   			h_id = result.getInt("h_id");
			   			
			   			p_r_01.setInt(1, m_id);
			   			r_p_s_01 = p_r_01.executeQuery();
			   			r_p_s_01.next();
			   			m_name = r_p_s_01.getString("m_name");
			   						
			   			p_r_02.setInt(1, h_id);
			   			r_p_s_02 = p_r_02.executeQuery();
			   			r_p_s_02.next();
			   			h_name = r_p_s_02.getString("h_name");
			   			h_seat_no = r_p_s_02.getInt("h_seat_no");
			   			h_f_seat_no = r_p_s_02.getInt("h_f_seat_no");
			   					
			   			temp_int = (int)(h_f_seat_no);
			   			a+=temp_int;
			   						
			   			temp_int2 = (int)(h_seat_no);
			   			b+=temp_int2;
			   						
			   			temp[i][0] = m_name;//movie name
			   			temp[i][1] = h_name;//hall name
			   			temp[i][2] = a; //free hall seats
			   			temp[i][3] = b; //no of hall seats

						i++;
			   						
			        }//while
				}//try
				catch(Exception e){
					System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
				}
				
				close();//close connection
				return temp;
	}//view_m_h()
	
	
	
	public void create_r(reservation obj){//create a new reservation
		open();
		
		int temp_code=0;
		
		try{
			//create values to be entry		
			p_id = obj.getP_id();
			f_name = obj.getF_name();
			l_name = obj.getL_name();
			phone_no = obj.getPhone();
			r_seat_no = obj.getNo_seats();
			
			//prepare the statement
			p_r_05 = con.prepareStatement(insertR);
		 	
		 	//put the values in the prepared statement
			p_r_05.setInt(1, p_id);
			p_r_05.setString(2,f_name);
			p_r_05.setString(3,l_name);
			p_r_05.setInt(4,phone_no);
			p_r_05.setInt(5,r_seat_no);
			//execute it
			p_r_05.executeQuery();
			
	
		}//try
		catch(Exception e){
					System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//catch
		close();
	}//create_r()
	

	public int get_r_id(reservation obj){
		
			int temp_code=0;
			//create values to be entry		
			p_id = obj.getP_id();
			f_name = obj.getF_name();
			l_name = obj.getL_name();
			phone_no = obj.getPhone();
			r_seat_no = obj.getNo_seats();
			
			open();
		try{	
			//find the r_id
			String re_r_id = "SELECT R.r_id FROM reservation R WHERE R.p_id=? AND R.f_name=? AND R.l_name=? AND R.phone=? AND R.no_seats=?";//find the r_id
			
			PreparedStatement stmtFIND = con.prepareStatement(re_r_id);
			stmtFIND.setInt(1, p_id);
			stmtFIND.setString(2,f_name);
			stmtFIND.setString(3,l_name);
			stmtFIND.setInt(4,phone_no);
			stmtFIND.setInt(5,r_seat_no);
			
			ResultSet result = stmtFIND.executeQuery();
			if (result.next()){
				temp_code=result.getInt("r_id");	
			}
		}
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//catch
		
		//PREPARING TO UPDATE THE TABLE
		int temp_h_id = 0;
		int temp_h_seats = 0;
		
		String FOUND_HALL = "SELECT P.h_id FROM plays P WHERE P.p_id=?";
		String FOUND_SEATS = "SELECT H.h_f_seat_no FROM hall H WHERE h_id=?";
		String UPDATE_SEATS = "UPDATE hall H SET H.h_f_seat_no=? WHERE H.h_id=?";
		
		try{
			
			PreparedStatement stmtF_H = con.prepareStatement(FOUND_HALL);
			stmtF_H.setInt(1, p_id);
		
			ResultSet resultF_H = stmtF_H.executeQuery();
			if (resultF_H.next()){
				temp_h_id=resultF_H.getInt("h_id");	
			}

			PreparedStatement stmtF_S = con.prepareStatement(FOUND_SEATS);
			stmtF_S.setInt(1, temp_h_id);
		
			ResultSet resultF_S = stmtF_S.executeQuery();
			if (resultF_S.next()){
				temp_h_seats=resultF_S.getInt("h_f_seat_no");	
			}
			
		//NOW UPDATING
			temp_h_seats = temp_h_seats - r_seat_no;

			PreparedStatement stmtU_S = con.prepareStatement(UPDATE_SEATS);
			stmtU_S.setInt(1, temp_h_seats);
			stmtU_S.setInt(2, temp_h_id);
		
			stmtU_S.executeQuery();

		
		}
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//catch
				
		//END UPDATE
			
		close();
		return temp_code;
	}//close get_r_id
	
	public boolean CheckSeats(reservation o){
		open();

		int seats=o.getNo_seats();
		int pid=o.getP_id();
		int temp_h_seats = 0;
		int temp_h_id = 0;
		boolean ok = false;
		String FOUND_HALL = "SELECT P.h_id FROM plays P WHERE P.p_id=?";
		String FOUND_SEATS = "SELECT H.h_f_seat_no FROM hall H WHERE h_id=?";
		
		//CHECKING
		try{
			
			PreparedStatement stmtF_H = con.prepareStatement(FOUND_HALL);
			stmtF_H.setInt(1, pid);
		
			ResultSet resultF_H = stmtF_H.executeQuery();
			if (resultF_H.next()){
				temp_h_id=resultF_H.getInt("h_id");	
			}
			PreparedStatement stmtF_S = con.prepareStatement(FOUND_SEATS);
			stmtF_S.setInt(1, temp_h_id);
			
			ResultSet resultF_S = stmtF_S.executeQuery();
			if (resultF_S.next()){
				temp_h_seats=resultF_S.getInt("h_f_seat_no");	
			}
			if ((temp_h_seats = temp_h_seats - seats)>0){ok=true;}
		}
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//catch
				
		//END CHECKING
		close();

		return ok;	
	}
	public boolean CheckSeats(int seats, String o){
		String Movie=o;
		boolean ok2 = false;
		int temp_seats2 = 0;
		String FOUND_SEATS_FROM = "SELECT H.h_f_seat_no FROM hall H WHERE H.h_id IN (SELECT P.h_id FROM plays P WHERE P.m_id IN (SELECT M.m_id FROM movie M WHERE M.m_name =?) )";
		System.out.println(o+ " "+seats );
		open();		
		try{
			System.out.println("BHKA");	
			PreparedStatement stmtF_S_F = con.prepareStatement(FOUND_SEATS_FROM);
			stmtF_S_F.setString(1, o);
			System.out.println(o);
	
			ResultSet resultF_S_F = stmtF_S_F.executeQuery();
			if (resultF_S_F.next()){
				temp_seats2=resultF_S_F.getInt("h_f_seat_no");	
			}
			//CHECKING
			System.out.println(temp_seats2);
			if ((temp_seats2 = temp_seats2 - seats)>0){ok2=true;}
		}
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//catch
				
		//END CHECKING
		close();
		System.out.println(ok2);
		return ok2;		
	}
	
	
	public int get_movie_id(String movie_name){
		open();
		String mn="";
		mn+=movie_name;
		try{
			 	
				p_r_03 = con.prepareStatement(get_m_id);
				p_r_03.setString(1, mn);
			
				r_p_s_03 = p_r_03.executeQuery();
				r_p_s_03.next();

				m_id = r_p_s_03.getInt("m_id");

							
		}//try
		catch(Exception e){
					System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//catch
		
		//stmt.executeUpdate();
		close();
		return m_id;			
	}//get_movie_id


	public int get_plays_id(int m_id)//gets the id of a plays using the movie id
	{
				open();
				
				try{
					 	p_r_04 = con.prepareStatement(get_p_id);
				
						p_r_04.setInt(1, m_id);
						r_p_s_04 = p_r_04.executeQuery();
						r_p_s_04.next();
						p_id = r_p_s_04.getInt("p_id");
				}//try
				catch(Exception e){
							System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
				}//catch
				
				
				close();
				return p_id;
	}//get_plays_id

	public void generate(int g_seats,String g_movie){//Just simple generate the tickets
		open();
		int temp_h_id = 0;
		int temp_free_seats = 0;
		
		try{
			String Found_Q = "SELECT P.h_id FROM plays P WHERE P.m_id IN (SELECT M.m_id FROM movie M WHERE M.m_name=?)";
			String Found_SEATSFREE = "SELECT H.h_f_seat_no FROM hall H WHERE h.h_id=?";
			String Update_Q = "UPDATE hall H SET H.h_f_seat_no=? WHERE H.h_id=?";
			
			PreparedStatement stmtF_Q = con.prepareStatement(Found_Q);
			stmtF_Q.setString(1, g_movie);
		
			ResultSet resultF_Q = stmtF_Q.executeQuery();
			if (resultF_Q.next()){
				temp_h_id=resultF_Q.getInt("h_id");	
			}
			
			//FIND FREE SEATS
			PreparedStatement stmtF_SEATSFREE = con.prepareStatement(Found_SEATSFREE);
			stmtF_SEATSFREE.setInt(1, temp_h_id);
		
			ResultSet resultF_SEATSFREE = stmtF_SEATSFREE.executeQuery();
			if (resultF_SEATSFREE.next()){
				temp_free_seats=resultF_SEATSFREE.getInt("h_f_seat_no");	
			}			
			System.out.println("GENERATE UPDATING");
			//UPDATE TABLE
			temp_free_seats = temp_free_seats - g_seats;
						System.out.println(temp_free_seats+ " "+g_movie+" "+temp_h_id );
		
			PreparedStatement stmtU_Q = con.prepareStatement(Update_Q);
			stmtU_Q.setInt(1, temp_free_seats);
			stmtU_Q.setInt(2, temp_h_id);
			System.out.println("GENERATE UPDATED");
			stmtU_Q.executeQuery();
		
		}//end of try
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//catch		
		close();
	}//end of generate
	
	public String getMovie(int pid){
		open();
		String temp_mov = "";
		
		try{
			
			String get_MOV = "SELECT M.m_name FROM movie M WHERE M.m_id IN (SELECT P.m_id FROM plays P WHERE P.p_id=?)";
			
			PreparedStatement stmtget_MOV = con.prepareStatement(get_MOV);
			stmtget_MOV.setInt(1, pid);
		
			ResultSet resultget_MOV = stmtget_MOV.executeQuery();
			if (resultget_MOV.next()){
				temp_mov=resultget_MOV.getString("m_name");	
			}
			
		}//end of try
		catch(Exception e){
			System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
		}//catch
		
		close();
		return temp_mov;	
	}

	public int movie_number(){//epistrefei to plithos tainiwn
		
		int m_n = 0;//plithos tainiwn sto pinaka movie
		
		open();
		
		try{
				String find_m_n = "SELECT COUNT(*) AS no_of_movies FROM movie ;";
				result = stmt.executeQuery(find_m_n);
				
				result.next();
				m_n = result.getInt("no_of_movies");
				
			}//try
			catch(Exception e){
				System.err.println("problems with SQL sent to "+url+": "+e.getMessage());
			}//catch
		
		close();
		return m_n;
		
	}//movie_number
}//class