package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingPojo {
    //1.adim : Tum key'ler icin private variable'lar olusturuyoruz
     private String firstname;
     private String lastname;
     private Integer totalprice;
     private Boolean depositpaid;
     public BookingDatesPojo bookingDates;
     public String additionalneeds;

     //2.adim: parametreli(Tum parametrelerle) ve parametresiz constructor olusturuyoruz
    public BookingPojo() { }
    public BookingPojo(String firstname, String lastname, Integer totalprice, Boolean depositpaid, BookingDatesPojo bookingDates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingDates = bookingDates;
        this.additionalneeds = additionalneeds;
    }
    //3.adim: Getters ve Setters olusturuyoruz
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
    public BookingDatesPojo getBookingDates() {
        return bookingDates;
    }
    public void setBookingDates(BookingDatesPojo bookingDates) {
        this.bookingDates = bookingDates;
    }
    public String getAdditionalneeds() {
        return additionalneeds;
    }
    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    //4.adim: toString() methodunu olusturuyoruz
    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingDates=" + bookingDates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}
