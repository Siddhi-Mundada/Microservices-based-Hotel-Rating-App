package com.example.user.service.entities;

//IMPPP- see not writing @Entity coz this is not stored in db
//no need to store table in db so dont use @Entity coz it is stored in Rating Service
public class Rating {

    //not writing @Id here
    //Same fields in Rating class of Rating Service
    private String ratingId;
    private String userId;    //kis user ne rate kiya
    private String hotelId;   //kis hotel ki rating hai
    private  int rating;      //1 star, 2 star
    private  String feedback;

    //IMPPP- this is not stored in db, just to get hotel details in user service
     private Hotel hotel;










    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public Rating() {
    }

    public Rating(String ratingId, String userId, String hotelId, int rating, String feedback) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.rating = rating;
        this.feedback = feedback;
    }
}
