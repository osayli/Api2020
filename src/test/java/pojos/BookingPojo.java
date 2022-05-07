package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingPojo {

     /*
   1)Create private variables for every key
   2)Create Constructor with all parameters and without any parameters
   3)Create getters and setters
   4)Create toString() Method
    */
    // 1)Create private variables for every key
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDatesPojo  bookingdates;
    private String additionalneeds;

    // 2)Create Constructor with all parameters and without any parameters

    public BookingPojo(String firstname, String lastname, Integer totalprice, Boolean depositpaid, BookingDatesPojo booingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = booingdates;
        this.additionalneeds = additionalneeds;
    }
    public BookingPojo() {
    }
    //3)Create getters and setters
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Integer getTotalprice() {
        return totalprice;
    }
    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }
    public Boolean getDepositpaid() {
        return depositpaid;
    }
    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }
    public BookingDatesPojo getBookingdates() {
        return bookingdates;
    }
    public void setBookingdates(BookingDatesPojo booingdates) {
        this.bookingdates = booingdates;
    }
    public String getAdditionalneeds() {
        return additionalneeds;
    }
    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
    // 4)Create toString() Method

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", booingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}
