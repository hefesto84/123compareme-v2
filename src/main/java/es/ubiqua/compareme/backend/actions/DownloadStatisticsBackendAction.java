package es.ubiqua.compareme.backend.actions;

import java.util.List;
import java.util.ArrayList;

import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;

public class DownloadStatisticsBackendAction extends BaseBackendAction {

    private static final long serialVersionUID = 1L;

    /**
     * url parameters
     */
    private String  dateIni;
    private String  dateEnd;
    private Integer otaId;
    private Integer hotelId;

    private List<Ota> otas = new ArrayList<Ota>();
    private List<Hotel> hotels = new ArrayList<Hotel>();
    private OtaManager otaManager = new OtaManager();
    private HotelManager hotelManager = new HotelManager();

    public String execute() {
        if (!isLogged()) {
            return ERROR;
        }

        Ota ota = new Ota();
        ota.setId(0);
        ota.setName("All");
        otas.add(ota);
        otas.addAll(otaManager.list());

        Hotel hotel = new Hotel();
        hotel.setId(0);
        hotel.setName("All");
        hotels.add(hotel);
        hotels.addAll(hotelManager.list(getLoggedCustomer()));

        return SUCCESS;
    }

    public void setDateIni(String dateIni) {
        this.dateIni = dateIni;
    }

    public String getDateIni() {
        return dateIni;
    }

    public void setDateEnd(String dateOut) {
        this.dateEnd = dateEnd;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setOtaId(Integer otaId) {
        this.otaId = otaId;
    }

    public Integer getOtaId() {
        return otaId == null ? 0 : otaId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getHotelId() {
        return hotelId == null ? 0 : hotelId;
    }

    public List<Ota> getOtas() {
        return otas;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }
}
