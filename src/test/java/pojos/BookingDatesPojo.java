package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {

    /*
1.adim : Tum key'ler icin private variable'lar olusturuyoruz
2.adim: parametreli(Tum parametrelerle) ve parametresiz constructor olusturuyoruz
3.adim: Getters ve Setters olusturuyoruz
4.adim: toString() methodunu olusturuyoruz
 */

    //1.adim : Tum key'ler icin private variable'lar olusturuyoruz

    private String checkin;
    private String checkout;

   // 2.adim: parametreli(Tum parametrelerle) ve parametresiz constructor olusturuyoruz
    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {
    }

    //3.adim: Getters ve Setters olusturuyoruz

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //4.adim: toString() methodunu olusturuyoruz

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
