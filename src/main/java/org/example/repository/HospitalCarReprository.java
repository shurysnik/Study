package org.example.repository;

import org.example.model.HospitalCar;

import java.util.LinkedList;
import java.util.List;

public class HospitalCarReprository implements  CrudRepository<HospitalCar>
{
    List<HospitalCar>hospitalCars;

    public HospitalCarReprository( ) {
        hospitalCars=new LinkedList<>();
    }

    @Override
    public HospitalCar getById(String id) {
         for (HospitalCar hospitalCar:hospitalCars){
             if (hospitalCar.getId().equals(id)){
                 return hospitalCar;
             }

         }
        return null;
    }

    @Override
    public List<HospitalCar> getAll() {
        return hospitalCars;
    }

    @Override
    public void save(HospitalCar hospitalCar) {
        hospitalCars.add(hospitalCar);
    }

    @Override
    public void saveAll(List<HospitalCar> hospitalCar) {
        hospitalCars.addAll(hospitalCar);
    }

    @Override
    public void update(HospitalCar hospitalCar) {
      final HospitalCar foundedHospitalCar= getById( hospitalCar.getId());
      if(foundedHospitalCar!=null)
      {
          HospitalCarsCopy.copy(hospitalCar,foundedHospitalCar);
      }
    }

    @Override
    public boolean delete(String id) {
       return   hospitalCars.removeIf(hospitalCar -> hospitalCar.getId().equals(id));
    }
    static  private   class HospitalCarsCopy{
     static void copy(HospitalCar to,HospitalCar from){
        to.setModel(from.getModel());
        to.setPrice(from.getPrice());
        to.setModel(from.getModel());
        to.setNumberOfPatients(from.getNumberOfPatients());
    }
    }
}
