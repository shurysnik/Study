package org.example.repository;

import org.example.model.SportCar;

import java.util.LinkedList;
import java.util.List;

public class SportCarReprository  implements  CrudRepository<SportCar> {
    List<SportCar>sportCars;

    public SportCarReprository( ) {
        sportCars=new LinkedList<>();
    }

    @Override
    public SportCar getById(String id) {
         for (SportCar sportCar : sportCars){
             if (sportCar.getId().equals(id))
             {
                 return sportCar;
             }
         }
         return  null;
    }

    @Override
    public List<SportCar> getAll() {
        return sportCars;
    }

    @Override
    public void save(SportCar sportCar) {
        sportCars.add(sportCar);
    }

    @Override
    public void saveAll(List<SportCar> sportCarsList) {
        sportCars.addAll(sportCarsList);
    }

    @Override
    public void update(SportCar sportCar) {
        final SportCar  foundedSportsCar=getById(sportCar.getId());
        if (foundedSportsCar!=null){
            SportCarsCopy.copy(sportCar,foundedSportsCar);
        }
    }

    @Override
    public boolean delete(String id) {
    return    sportCars.removeIf(sportCar -> sportCar.getId().equals(id));
    }
    private  static class SportCarsCopy{
          static void copy(SportCar to,SportCar from){
            to.setModel(from.getModel());
            to.setPrice(from.getPrice());
            to.setNumberOfRacesWon(from.getNumberOfRacesWon());
            to.setMaxSpeed(from.getMaxSpeed());
        }
    }
}
