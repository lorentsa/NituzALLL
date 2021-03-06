package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacationModel implements IModel{

    @Override
    public Connection connect() {
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
    public boolean CreateVacation(Vacation vacation) {
        String sql = "INSERT INTO Vacations(id, flight_company, departure_date, back_date, baggage_included, country,flight_back_included,num_tickets_adult,num_tickets_kid,num_tickets_baby,vacation_kind,hotel_included,rank_hotel,hotel_kind,user_name) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(vacation.getId()));


                pstmt.setString(2, vacation.getFlightCompany());


                pstmt.setString(3, vacation.getDepartureDate());


                pstmt.setString(4, vacation.getBackDate());

                pstmt.setString(5,vacation.getBaggageIncluded());


                 pstmt.setString(6,vacation.getCountry());


                pstmt.setString(7,vacation.getFlightBackIncluded());


                pstmt.setString(8,String.valueOf(vacation.getNumOfTicketsAdult()));


                pstmt.setString(9,String.valueOf(vacation.getNumOfTicketsKid()));


                pstmt.setString(10,String.valueOf(vacation.getNumOfTicketsBaby()));


                pstmt.setString(11,vacation.getVacationKind());


                pstmt.setString(12, vacation.getHotelIncluded());


                pstmt.setString(13, String.valueOf(vacation.getRankOfHotel()));

            pstmt.setString(14, vacation.getKindOfHotel());


            pstmt.setString(15, vacation.getSeller());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //read
    public List<Vacation> findVacations(String flightCompany,String departureDate,String backDate,String baggageIncluded,
            String Country,String flightBackIncluded,int numOfTicketsAdult,int numOfTicketsChild,int numOfTicketsBaby,
                                        String vacationKind,String hotelIncluded,int rankOfHotel,String kindOfHotel){
        String sql="SELECT id, flight_company, departure_date, back_date, baggage_included, country, " +
                "flight_back_included, num_tickets_adult, num_tickets_kid ,num_tickets_baby," +
                "vacation_kind,hotel_included,rank_hotel,hotel_kind,user_name FROM Vacations ";
        String constraints="";
        List<Vacation> vacations=new ArrayList<Vacation>();
        boolean [] isAdded=new boolean[13];
        boolean somethingAdded=false;
        for(int i=0;i<13;i++){
            isAdded[i]=false;
        }
        if(!flightCompany.equals("")){
            constraints=constraints+" flight_company=?";
            isAdded[0]=true;
            somethingAdded=true;
        }
        if(!departureDate.equals("")){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" departure_date=?";
            isAdded[1]=true;
            somethingAdded=true;
        }
        if(!backDate.equals("")){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" back_date=?";
            isAdded[2]=true;
            somethingAdded=true;
        }
        if(!baggageIncluded.equals("")){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" baggage_included=?";
            isAdded[3]=true;
            somethingAdded=true;
        }
        if(!Country.equals("")){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" country=?";
            isAdded[4]=true;
            somethingAdded=true;
        }
        if(!flightBackIncluded.equals("")){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" flight_back_included=?";
            isAdded[5]=true;
            somethingAdded=true;
        }
        if(numOfTicketsAdult!=-1){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" num_tickets_adult=?";
            isAdded[6]=true;
            somethingAdded=true;
        }
        if(numOfTicketsChild!=-1){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" num_tickets_kid=?";
            isAdded[7]=true;
            somethingAdded=true;
        }
        if(numOfTicketsBaby!=-1){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" num_tickets_baby=?";
            isAdded[8]=true;
            somethingAdded=true;
        }
        if(!vacationKind.equals("")){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" flight_back_included=?";
            isAdded[9]=true;
            somethingAdded=true;
        }
        if(!hotelIncluded.equals("")){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" hotel_included=?";
            isAdded[10]=true;
            somethingAdded=true;
        }
        if(rankOfHotel!=-1){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" rank_hotel=?";
            isAdded[11]=true;
            somethingAdded=true;
        }
        if(!kindOfHotel.equals("")){
            if(somethingAdded)
                constraints=constraints+ "AND";
            constraints=constraints+" hotel_kind=?";
            isAdded[12]=true;
            somethingAdded=true;
        }
        if(somethingAdded) {
            sql = sql + "WHERE" + constraints;
        }
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


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
                        index+=1;
                    }
                    if (isAdded[12]) {
                         pstmt.setString(index, kindOfHotel);
                    }




                    //show
            int id_;
            String flightCompany_;
            String departureDate_;
            String backDate_;
            String baggageIncluded_;
            String Country_;
            String flightBackIncluded_;
            int numOfTicketsAdult_;
            int numOfTicketsChild_;
            int numOfTicketsBaby_;
            String vacationKind_;
            String hotelIncluded_;
            int rankOfHotel_;
            String kindOfHotel_;
            String userName_;
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    id_=Integer.parseInt(rs.getString(1));
                    flightCompany_=rs.getString(2);
                    departureDate_= rs.getString(3);
                    backDate_=rs.getString(4);
                    baggageIncluded_=rs.getString(5);
                    Country_=rs.getString(6);
                    flightBackIncluded_ =rs.getString(7);
                    numOfTicketsAdult_=Integer.parseInt(rs.getString(8));
                    numOfTicketsChild_=Integer.parseInt(rs.getString(9));
                    numOfTicketsBaby_=Integer.parseInt(rs.getString(10));
                    vacationKind_=rs.getString(11);
                    hotelIncluded_=rs.getString(12);
                    rankOfHotel_=Integer.parseInt(rs.getString(13));
                    kindOfHotel_=rs.getString(14);
                    userName_=rs.getString(15);
                    Vacation vacation = new Vacation(id_,flightCompany_ ,departureDate_, backDate_, baggageIncluded_, Country_, flightBackIncluded_,numOfTicketsAdult_,
                            numOfTicketsChild_, numOfTicketsBaby_,vacationKind_ ,hotelIncluded_, rankOfHotel_,kindOfHotel_, userName_);
                    vacations.add(vacation);
                }
                return vacations;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }



        return null;

    }
}
