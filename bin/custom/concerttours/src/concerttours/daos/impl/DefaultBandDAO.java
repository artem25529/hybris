package concerttours.daos.impl;

import concerttours.daos.BandDAO;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "bandDAO")
public class DefaultBandDAO implements BandDAO {
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<BandModel> findBands() {
        final String queryString =
                "SELECT {p:" + BandModel.PK + "} "
                        + "FROM {" + BandModel._TYPECODE + " AS p}";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }

    @Override
    public List<BandModel> findBandsByCode(String code) {
        final String queryString =
                "SELECT {p:" + BandModel.PK + "} "
                        + "FROM {" + BandModel._TYPECODE + " AS p} "
                        + "WHERE " + "{p:" + BandModel.CODE + "}=?code";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }
}
