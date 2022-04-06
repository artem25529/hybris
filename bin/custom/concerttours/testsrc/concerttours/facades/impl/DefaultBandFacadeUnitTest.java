package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultBandFacadeUnitTest {
    private DefaultBandFacade bandFacade;
    private ModelService modelService;
    private BandService bandService;

    private static final String BAND_CODE = "ROCK-11";
    private static final String BAND_NAME = "Ladies of Rock";
    private static final Long ALBUMS_SOLD = 42000L;
    private static final String BAND_HISTORY = "All female rock band formed in Munich in the late 1990s";

    private List<BandModel> dummyDataBandList() {
        final List<BandModel> bands = new ArrayList<>();
        final BandModel band = configTestBand();
        bands.add(band);
        return bands;
    }

    private BandModel configTestBand() {
        final BandModel band = new BandModel();
        band.setCode(BAND_CODE);
        modelService.attach(band);
        band.setName(BAND_NAME);
        band.setAlbumSales(ALBUMS_SOLD);
        band.setHistory(BAND_HISTORY);
        return band;
    }

    @Before
    public void setUp() {
        bandFacade = new DefaultBandFacade();
        modelService = mock(ModelService.class);
        bandService = mock(BandService.class);
        bandFacade.setBandService(bandService);
    }

    @Test
    public void testGetAllBands() {
        final List<BandModel> bands = dummyDataBandList();
        final BandModel band = configTestBand();
        when(bandService.getBands()).thenReturn(bands);

        final List<BandData> dto = bandFacade.getBands();
        Assert.assertNotNull(dto);
        Assert.assertEquals(bands.size(), dto.size());
        Assert.assertEquals(band.getCode(), dto.get(0).getId());
        Assert.assertEquals(band.getName(), dto.get(0).getName());
        Assert.assertEquals(band.getAlbumSales(), dto.get(0).getAlbumsSold());
        Assert.assertEquals(band.getHistory(), dto.get(0).getDescription());
    }

    @Test
    public void testGetBand() {
        final BandModel band = configTestBand();
        when(bandService.getBandForCode(BAND_CODE)).thenReturn(band);

        final BandData dto = bandFacade.getBand(BAND_CODE);
        Assert.assertNotNull(dto);
        Assert.assertEquals(band.getCode(), dto.getId());
        Assert.assertEquals(band.getName(), dto.getName());
        Assert.assertEquals(band.getAlbumSales(), dto.getAlbumsSold());
        Assert.assertEquals(band.getHistory(), dto.getDescription());
    }
}