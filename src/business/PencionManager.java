package business;

import core.Helper;
import Dao.PencionDao;
import entity.Hotel;
import entity.Pencion;

import java.util.ArrayList;

//Pansiyonları yöneten sınıf.
public class PencionManager {
    private final PencionDao pencionDao;
    private Hotel hotel;

    //PencionManager sınıfının yapıcı metodu.
    public PencionManager(Hotel hotel) {
        this.hotel = hotel;
        this.pencionDao = new PencionDao();
    }

    /*
    Yeni bir pansiyon kaydı oluşturur.
    * @param hotel Otel nesnesi
    * @param val   Pansiyon tipi
    * @return Kayıt işlemi başarılıysa true, aksi takdirde false
    */
    public boolean savePencion(Hotel hotel, String val) {
        if (hotel.getId() != 0) {
            // Otel ID'si 0 olmayan bir pansiyon kaydı oluşturulduğunda başarılı mesajı gösterilir.
            Helper.showMsg("done");

        }
        return this.pencionDao.savePencion(hotel, val);
    }


    /*
     * Pansiyon listesini belirtilen boyutta bir nesne dizisi olarak getirir.
     * @param size    Nesne dizisinin boyutu
     * @param pencions Pansiyon listesi
     * @return Belirtilen boyutta bir nesne dizisi
     */
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pencion> pencions) {
        ArrayList<Object[]> pencionList = new ArrayList<>();
        for (Pencion obj : pencions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getPencionId();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getPencionType();

            pencionList.add(rowObject);
        }
        return pencionList;
    }


    /*
     * Tüm pansiyonları getirir.
     * @return Tüm pansiyonlar
     */
    public ArrayList<Pencion> findAll() {
        return this.pencionDao.findAll();
    }


    /*
     * Belirtilen otel ID'sine ait pansiyonları getirir.
     * @param hotel_id Otel ID'si
     * @return Belirtilen otel ID'sine ait pansiyonlar
     */
    public ArrayList<Pencion> findByHotelId(int hotel_id) {
        return this.pencionDao.findByHotelId(hotel_id);
    }
}