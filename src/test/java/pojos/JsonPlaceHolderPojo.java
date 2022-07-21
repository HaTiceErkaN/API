package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//private olarak belirtiklerimin disinda farkli bir sey varsa dikkate almaz
public class JsonPlaceHolderPojo {

/*
1.adim : Tum key'ler icin private variable'lar olusturuyoruz
2.adim: parametreli(Tum parametrelerle) ve parametresiz constructor olusturuyoruz
3.adim: Getters ve Setters olusturuyoruz
4.adim: toString() methodunu olusturuyoruz
 */

    //1.adim : Tum key'ler icin private variable'lar olusturuyoruz
    private Integer userId;
    private String title;
    private Boolean completed;

    //2.adim: parametreli(Tum parametrelerle) ve parametresiz constructor olusturuyoruz

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {

    }

    //3.adim: Getters ve Setters olusturuyoruz


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //4.adim: toString() methodunu olusturuyoruz //private olarak verilen variable'lari string bir sekilde okumamizi sagliyor


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    //Farklı key-value ikililerin uyuşmazlığını @JsonIgnoreProperties(ignoreUnknown = true)
    // anotation'ını Pojo Class'ımızın başına yazarak çözebiliriz.
}
