package br.com.emiyoshi.partner.usecase;

import br.com.emiyoshi.partner.domain.Address;
import br.com.emiyoshi.partner.domain.AddressType;
import br.com.emiyoshi.partner.domain.CoverageArea;
import br.com.emiyoshi.partner.domain.CoverageAreaType;
import br.com.emiyoshi.partner.domain.Pdv;
import br.com.emiyoshi.partner.domain.Point;
import br.com.emiyoshi.partner.repository.PdvRepository;
import br.com.emiyoshi.partner.usecase.exception.PartnerNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SearchPdvTest {

    @Autowired
    private SearchPdv searchPdv;

    @Autowired
    private PdvRepository pdvRepository;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("given partner id when find partner by id then return partner")
    @Test
    public void findPartnerSucess() {
        // given
        Long id = 1L;
        String ownerName = "Eduardo";
        String cnpj = "41916105000172";
        createPdv(id, cnpj, ownerName);

        // when
        final Pdv pdv = searchPdv.findByIdQuery(1L);

        // then
        Assert.assertEquals(id, pdv.getId());
        Assert.assertEquals(ownerName, pdv.getOwnerName());
        Assert.assertEquals(cnpj, pdv.getDocument());
    }

    @DisplayName("given partner id when partner not found then throw exception")
    @Test
    public void partnerNotFound() {
        // given
        Long id = 1L;
        String ownerName = "Eduardo";
        String cnpj = "41916105000172";
        createPdv(id, cnpj, ownerName);

        // when
        Assertions.assertThrows(PartnerNotFoundException.class, () -> {
            final Pdv pdv = searchPdv.findByIdQuery(2L);
        });

    }

    @DisplayName("given longitude and latitude when search nearest partner then return success")
    @Test
    public void findNearestPartnerSuccess() {
        // given
        createPdvsDiffCoordinates();

        // when
        final Pdv pdv = searchPdv.findByCoordinate(10.0, 10.0);

        // then
        Assert.assertEquals(Long.valueOf(50L), pdv.getId());
        Assert.assertEquals("Eduardo Piroco", pdv.getOwnerName());
        Assert.assertEquals("82.666.231/0001-01", pdv.getDocument());
        Assert.assertEquals("Adega Emporio", pdv.getTradingName());
    }

    private void createPdv(Long id, String document, String ownerName) {
        final Address address =
                Address.builder().type(AddressType.POINT).coordinates(new Point(1.0, 1.0)).build();
        final ArrayList<List<List<Point>>> coordinates = new ArrayList<>();
        coordinates.add(List.of(List.of(new Point(1.0, 1.0))));
        final CoverageArea coverageArea =
                CoverageArea.builder().type(CoverageAreaType.MULTIPOLYGON).coordinates(coordinates).build();
        final Pdv pdv =
                Pdv.builder()
                        .id(id)
                        .address(address)
                        .coverageArea(coverageArea)
                        .tradingName("Trading Name Test")
                        .ownerName(ownerName)
                        .document(document)
                        .build();
        pdvRepository.save(pdv);
    }

    private void createPdvsDiffCoordinates() {
        final Address address =
                Address.builder().type(AddressType.POINT).coordinates(new Point(1.0, 1.0)).build();
        final ArrayList<List<List<Point>>> coordinates = new ArrayList<>();
        coordinates.add(List.of(List.of(
                new Point(-67.83039, -9.95782),
                new Point(-67.83176, -9.98487),
                new Point(-67.78627, -9.98825),
                new Point(-67.78885, -9.95105),
                new Point(-67.83039, -9.95782)
        )));

        final CoverageArea coverageArea =
                CoverageArea.builder().type(CoverageAreaType.MULTIPOLYGON).coordinates(coordinates).build();
        final Pdv pdv =
                Pdv.builder()
                        .id(50L)
                        .address(address)
                        .coverageArea(coverageArea)
                        .tradingName("Adega Emporio")
                        .ownerName("Eduardo Piroco")
                        .document("82.666.231/0001-01")
                        .build();

        final Address address2 =
                Address.builder().type(AddressType.POINT).coordinates(new Point(1.0, 1.0)).build();
        final ArrayList<List<List<Point>>> coordinates2 = new ArrayList<>();
        coordinates2.add(List.of(List.of(
                new Point(-43.11539, -22.88063),
                new Point(-43.13002,-22.87529),
                new Point(-43.13796,-22.88383),
                new Point(-43.13955,-22.91103),
                new Point(-43.1168,-22.92096),
                new Point(-43.11117,-22.92854),
                new Point(-43.10743,-22.93658),
                new Point(-43.1,-22.93573),
                new Point(-43.08382,-22.92934),
                new Point(-43.07583,-22.91434),
                new Point(-43.0881,-22.90551),
                new Point(-43.09556,-22.8932),
                new Point(-43.09565,-22.88964),
                new Point(-43.10655,-22.89217),
                new Point(-43.11539,-22.88063)
        )));

        final CoverageArea coverageArea2 =
                CoverageArea.builder().type(CoverageAreaType.MULTIPOLYGON).coordinates(coordinates2).build();

        final Pdv pdv2 =
                Pdv.builder()
                        .id(38L)
                        .address(address2)
                        .coverageArea(coverageArea2)
                        .tradingName("SOS Cerveja")
                        .ownerName("Felipe Rudenberg")
                        .document("24302190/0001-60")
                        .build();

        pdvRepository.save(pdv);
        pdvRepository.save(pdv2);

    }
}
