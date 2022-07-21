package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GorestResponsePojo {
    /*
    1.adim private variable olustur
    2.adim parametreli ve parametresiz constructor olustur
    3.adim getter setter olustur
    4.adim toString() olustur
     */

    // 1.adim private variable olustur
    private Object meta;
    private GorestDataPojo data;

    //  2.adim parametreli ve parametresiz constructor olustur


    public GorestResponsePojo() {
    }

    public GorestResponsePojo(String meta, GorestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    //  3.adim getter setter olustur

    public Object getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public GorestDataPojo getData() {
        return data;
    }

    public void setData(GorestDataPojo data) {
        this.data = data;
    }


    // 4.adim toString() olustur


    @Override
    public String toString() {
        return "GorestResponsePojo{" +
                "meta='" + meta + '\'' +
                ", data=" + data +
                '}';
    }
}
