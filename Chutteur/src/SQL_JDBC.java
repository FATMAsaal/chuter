import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class SQL_JDBC {
	// OPEN OR CREATE DATABASE
	public boolean openDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
		return true;
	}

	// CREATE TABLES
	public boolean createTable() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();

			String User =	"DROP TABLE IF EXISTS User; 			\n"
						+	"CREATE TABLE User 						(\n"
						+	"	ID		TINYINT		PRIMARY KEY,	\n"
						+	"	NAME	VARCHAR(20)	NOT NULL,		\n"
						+	"	EMAIL	VARCHAR(20)	NOT NULL		);";

			String Temp =	"DROP TABLE IF EXISTS Temp; 			\n"
						+	"CREATE TABLE Temp				 		(\n"
						+	"	FAHRENHEIT	REAL,					\n"
						+	"	CELSIUS		REAL	NOT NULL,		\n"
						+	"	HUMIDITY	REAL	NOT NULL		);";

			String Sound =	"DROP TABLE IF EXISTS Sound; 			\n"
						+	"CREATE TABLE Sound						(\n"
						+	"	SOUND	INTEGER						);";

			String LED = 	"DROP TABLE IF EXISTS LED; 				\n"
						+	"CREATE TABLE LED				 		(\n"
						+	"	TURNEDON	TINYINT(1)				);";

			String Light =	"DROP TABLE IF EXISTS Light; 			\n"
						+	"CREATE TABLE Light						(\n"
						+	"	INTENSITY	INTEGER					);";

			stmt.executeUpdate(User);
			stmt.executeUpdate(Temp);
			stmt.executeUpdate(Sound);
			stmt.executeUpdate(LED);
			stmt.executeUpdate(Light);

			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
		return true;
	}

	// INSERT VALUES
	public boolean insertUser(int ID, String NAME, String EMAIL){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();

			stmt.executeUpdate("INSERT INTO User(ID,NAME,EMAIL) VALUES ("+ID+",'"+NAME+"','"+EMAIL+"');");

			stmt.close();
			c.close();
			return true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public boolean insertUser(List<Integer> ID, List<String> NAME, List<String> EMAIL){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();

			for(int i=0; i<ID.size(); i++){
				stmt.executeUpdate("INSERT INTO User(ID,NAME,EMAIL) VALUES ("+ID.get(i)+",'"+NAME.get(i)+"','"+EMAIL.get(i)+"');");
			}
			stmt.close();
			c.close();
			return true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public boolean insertTemp(float FAHRENHEIT, float CELSIUS, float HUMIDITY){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();

			stmt.executeUpdate("INSERT INTO Temp(FAHRENHEIT,CELSIUS,HUMIDITY) VALUES ("+FAHRENHEIT+","+CELSIUS+","+HUMIDITY+");");

			stmt.close();
			c.close();
			return true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public boolean insertTemp(List<Float> FAHRENHEIT, List<Float> CELSIUS, List<Float> HUMIDITY){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();

			for(int i=0;i<FAHRENHEIT.size();i++){
				stmt.executeUpdate("INSERT INTO Temp(FAHRENHEIT,CELSIUS,HUMIDITY) VALUES ("
					+	FAHRENHEIT.get(i)+","+CELSIUS.get(i)+","+HUMIDITY.get(i)+");");
			}
			stmt.close();
			c.close();
			return true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public boolean insertSound(int SOUND){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();

			stmt.executeUpdate("INSERT INTO Sound(SOUND) VALUES ("+SOUND+");");

			stmt.close();
			c.close();
			return true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public boolean insertSound(List<Integer> SOUND){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();

			for(int i=0;i<SOUND.size(); i++){
				stmt.executeUpdate("INSERT INTO Sound(SOUND) VALUES ("+SOUND.get(i)+");");
			}
			stmt.close();
			c.close();
			return true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public boolean insertLight(int INTENSITY){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();

			stmt.executeUpdate("INSERT INTO Light (INTENSITY) VALUES ("+INTENSITY+");");

			stmt.close();
			c.close();
			return true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public boolean insertLight(List<Integer> INTENSITY){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();
			for(int i=0;i<INTENSITY.size(); i++){
				stmt.executeUpdate("INSERT INTO Light (INTENSITY) VALUES ("+INTENSITY.get(i)+");");
			}	
			stmt.close();
			c.close();
			return true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	// SELECT
	public String[][] selectUsers(){
		String[][] resUser;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");

			Statement stmt = c.createStatement();
			ResultSet res = stmt.executeQuery("select * from User;");

			int size = 0;
			while(res.next()){ size++; }
			res.close();

			resUser = new String[size][3];

			res = stmt.executeQuery("select * from User;");

			int i = 0;

			while(res.next()){
				resUser[i][0] = res.getString(1);	// ID
				resUser[i][1] = res.getString(2);	// Name
				resUser[i][2] = res.getString(3);	// Email
				i++;
			}
			
			res.close();
			stmt.close();			
			c.close();
			return resUser;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public List<Float> selectFahrenheit(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			
			Statement stmt = c.createStatement();
			ResultSet res = stmt.executeQuery("select FAHRENHEIT from Temp;");
			
			List<Float> resFahrenheit = new ArrayList<>();
			while(res.next()){ resFahrenheit.add(res.getFloat(1)); }
			
			res.close();
			stmt.close();			
			c.close();
			return resFahrenheit;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public List<Float> selectCelsius(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			
			Statement stmt = c.createStatement();
			ResultSet res = stmt.executeQuery("select CELSIUS from Temp;");
			
			List<Float> resCelsius = new ArrayList<>();
			while(res.next()){ resCelsius.add(res.getFloat(1)); }
			
			res.close();
			stmt.close();			
			c.close();
			return resCelsius;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public List<Float> selectHumidity(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			
			Statement stmt = c.createStatement();
			ResultSet res = stmt.executeQuery("select HUMIDITY from Temp;");
			
			List<Float> resHumidity = new ArrayList<>();
			while(res.next()){
				resHumidity.add(res.getFloat(1));
			}
			
			res.close();
			stmt.close();			
			c.close();
			return resHumidity;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public List<Integer> selectSound(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			
			Statement stmt = c.createStatement();
			ResultSet res = stmt.executeQuery("select * from Sound;");
			
			List<Integer> resSound = new ArrayList<>();
			while(res.next()){ resSound.add(res.getInt(1)); }
			
			res.close();
			stmt.close();			
			c.close();
			return resSound;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public List<Integer> selectLight(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			
			Statement stmt = c.createStatement();
			ResultSet res = stmt.executeQuery("select * from Light;");
			
			List<Integer> resLight = new ArrayList<>();
			while(res.next()){ resLight.add(res.getInt(1)); }
			
			res.close();
			stmt.close();			
			c.close();
			return resLight;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
	}
}