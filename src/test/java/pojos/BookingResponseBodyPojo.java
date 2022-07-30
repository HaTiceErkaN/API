package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponseBodyPojo {

    //1.adim : Tum key'ler icin private variable'lar olusturuyoruz
    private Integer bookingid;
    private BookingPojo booking;

    //2.adim: parametreli(Tum parametrelerle) ve parametresiz constructor olusturuyoruz

    public BookingResponseBodyPojo() {
    }

    public BookingResponseBodyPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    //3.adim: Getters ve Setters olusturuyoruz
    public Integer getBookingid() {
        return bookingid;
    }
    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }
    public BookingPojo getBooking() {
        return booking;
    }
    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }


    //4.adim: toString() methodunu olusturuyoruz
    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
