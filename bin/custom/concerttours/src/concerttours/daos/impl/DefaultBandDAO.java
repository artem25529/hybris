package concerttours.daos.impl;

import concerttours.daos.BandDAO;
import concerttours.model.BandModel;

import java.util.List;

public class DefaultBandDAO implements BandDAO {
    @Override
    public List<BandModel> findBands() {
        return null;
    }

    @Override
    public List<BandModel> findBandsByCode(String code) {
        return null;
    }
}
