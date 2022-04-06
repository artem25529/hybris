package concerttours.service;

import concerttours.model.BandModel;

import java.util.List;

public interface BandService {
    List<BandModel> getBands();
    BandModel getBandForCode(String code);
}
