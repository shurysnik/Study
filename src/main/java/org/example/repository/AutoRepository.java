package org.example.repository;

import org.example.model.Auto;

import java.util.LinkedList;
import java.util.List;

public class AutoRepository implements CrudRepository<Auto> {
    private final List<Auto> autos;

    public AutoRepository() {
        autos = new LinkedList<>();
    }

    @Override
    public Auto getById(String id) {
        for (Auto auto : autos) {
            if (auto.getId().equals(id)) {
                return auto;
            }
        }
        return null;
    }

    @Override
    public List<Auto> getAll() {
        return autos;
    }

    @Override
    public void save(Auto auto) {

          autos.add(auto);

    }

    @Override
    public void saveAll(List<Auto> autos) {

        autos.addAll(autos);
    }

    @Override
    public void update(Auto auto) {
        final Auto foundedAuto = getById(auto.getId());
        if (foundedAuto != null) {
            AutoCopy.copy(auto, foundedAuto);
        }
    }

    @Override
    public boolean delete(String id) {
        return autos.removeIf(auto -> auto.getId().equals(id));
    }

    private static class AutoCopy {
        static void copy(final Auto from, final Auto to) {
            to.setModel(from.getModel());
            to.setBodyType(from.getBodyType());
            to.setPrice(from.getPrice());
        }
    }
}
