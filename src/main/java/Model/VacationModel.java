package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VacationModel {

    //connection function
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Users.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //create
    public boolean Create(Vacation vacation) {
        String sql = "INSERT INTO Vacations(id, flight_company, departure_date, back_date, baggage_included, country,flight_back_included,num_tickets_adult,num_ticket_kid,num_tickets_baby,vacation_kind,hotel_included,rank_hotel,user_name) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(vacation.getId()));

            if(vacation.getFlightCompany().equals(""))
                pstmt.setString(2, null);
            else
                pstmt.setString(2, vacation.getFlightCompany());

            if(vacation.getDepartureDate().equals(""))
                pstmt.setString(3, null);
            else
                pstmt.setString(3, vacation.getDepartureDate());

            if(vacation.getBackDate().equals(""))
                pstmt.setString(4, null);
            else
                pstmt.setString(4, vacation.getBackDate());

            if(vacation.getBaggageIncluded().equals(""))
                pstmt.setString(5, null);
            else
                pstmt.setString(5,vacation.getBaggageIncluded());

            if(vacation.getCountry().equals(""))
                pstmt.setString(6, null);
            else
                 pstmt.setString(6,vacation.getCountry());

            if(vacation.getFlightBackIncluded().equals(""))
                pstmt.setString(7, null);
            else
                pstmt.setString(7,vacation.getFlightBackIncluded());

            if(vacation.getNumOfTicketsAdult()==-1)
                pstmt.setString(8, null);
            else
                pstmt.setString(8,String.valueOf(vacation.getNumOfTicketsAdult()));

            if(vacation.getNumOfTicketsKid()==-1)
                pstmt.setString(9, null);
            else
                pstmt.setString(9,String.valueOf(vacation.getNumOfTicketsKid()));

            if(vacation.getNumOfTicketsBaby()==-1)
                pstmt.setString(10, null);
            else
                pstmt.setString(10,String.valueOf(vacation.getNumOfTicketsBaby()));

            if(vacation.getVacationKind().equals(""))
                pstmt.setString(11, null);
            else
                pstmt.setString(11,vacation.getVacationKind());

            if(vacation.getHotelIncluded().equals(""))
                pstmt.setString(12, null);
            else
                pstmt.setString(12, vacation.getHotelIncluded());

            if(vacation.getRankOfHotel()==-1)
                pstmt.setString(13, null);
            else
                pstmt.setString(13, String.valueOf(vacation.getRankOfHotel()));

            pstmt.setString(14, vacation.getUserName());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //read
    public List<Vacation> findVacations(String flightCompany,String departureDate,String backDate,String baggageIncluded,
            String Country,String flightBackIncluded,int numOfTicketsAdult,int numOfTicketsChild,int numOfTicketsBaby,String vacationKind,String hotelIncluded,int rankOfHotel){
        String sql="SELECT id, flight_company, departure_date, back_date, baggage_included, country,flight_back_included,num_tickets_adult,num_ticket_kid,num_tickets_baby,vacation_kind,hotel_included,rank_hotel,user_name FROM Vacations ";
        String constraints="";
        List<Vacation> vacations=new ArrayList<Vacation>();
        boolean [] isAdded=new boolean[12];
        for(int i=0;i<10;i++){
            isAdded[i]=false;
        }
        if(!flightCompany.equals("")){
            constraints=constraints+" flight_company=?";
            isAdded[0]=true;
        }
        if(!departureDate.equals("")){
            constraints=constraints+" departure_date=?";
            isAdded[1]=true;
        }
        if(!backDate.equals("")){
            constraints=constraints+" back_date=?";
            isAdded[2]=true;
        }
        if(!baggageIncluded.equals("")){
            constraints=constraints+" baggage_included=?";
            isAdded[3]=true;
        }
        if(!Country.equals("")){
            constraints=constraints+" country=?";
            isAdded[4]=true;
        }
        if(!flightBackIncluded.equals("")){
            constraints=constraints+" flight_back_included=?";
            isAdded[5]=true;
        }
        if(numOfTicketsAdult!=-1){
            constraints=constraints+" num_tickets_adult=?";
            isAdded[6]=true;
        }
        if(numOfTicketsChild!=-1){
            constraints=constraints+" num_tickets_kid=?";
            isAdded[7]=true;
        }
        if(numOfTicketsBaby!=-1){
            constraints=constraints+" num_tickets_baby=?";
            isAdded[8]=true;
        }
        if(!vacationKind.equals("")){
            constraints=constraints+" flight_back_included=?";
            isAdded[9]=true;
        }
        if(!hotelIncluded.equals("")){
            constraints=constraints+" hotel_included=?";
            isAdded[10]=true;
        }
        if(rankOfHotel!=-1){
            constraints=constraints+" rank_hotel=?";
            isAdded[11]=true;
        }
        Vector<String> ans = new Vector<>();
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
        if(isAdded[0]||isAdded[1]||isAdded[2]||isAdded[3]||isAdded[4]||isAdded[5]||isAdded[6]||isAdded[7]||isAdded[8]||isAdded[9]||isAdded[10]||isAdded[11]) {
            sql = sql + "WHERE" + constraints;


            // set the values
            int index = 1;
            if (isAdded[0]) {
                pstmt.setString(index, flightCompany);
                index += 1;
            }
            if (isAdded[1]) {
                pstmt.setString(index, departureDate);
                index += 1;
            }
            if (isAdded[2]) {
                pstmt.setString(index, backDate);
                index += 1;
            }
            if (isAdded[3]) {
                pstmt.setString(index, baggageIncluded);
                index += 1;
            }
            if (isAdded[4]) {
                pstmt.setString(index, Country);
                index += 1;
            }
            if (isAdded[5]) {
                pstmt.setString(index, flightBackIncluded);
                index += 1;
            }
            if (isAdded[6]) {
                pstmt.setString(index, String.valueOf(numOfTicketsAdult));
                index += 1;
            }
            if (isAdded[7]) {
                pstmt.setString(index, String.valueOf(numOfTicketsChild));
                index += 1;
            }
            if (isAdded[8]) {
                pstmt.setString(index, String.valueOf(numOfTicketsBaby));
                index += 1;
            }
            if (isAdded[9]) {
                pstmt.setString(index, vacationKind);
                index += 1;
            }
            if (isAdded[10]) {
                pstmt.setString(index, hotelIncluded);
                index += 1;
            }
            if (isAdded[11]) {
                pstmt.setString(index, String.valueOf(rankOfHotel));
            }
        }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Vacation vacation=new Vacation(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(6),Integer.parseInt(rs.getString(7)),Integer.parseInt(rs.getString(8)),Integer.parseInt(rs.getString(9)),
                        rs.getString(10),rs.getString(11),Integer.parseInt(rs.getString(12)),rs.getString(13));
                vacations.add(vacation);
            }
            return vacations;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }




        return null;

    }
}