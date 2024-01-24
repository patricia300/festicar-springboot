package com.bdi.projectbdigroup5.populating.controller;

import com.bdi.projectbdigroup5.model.*;
import com.bdi.projectbdigroup5.populating.model.CommuneDepartementRegionDataGouv;
import com.bdi.projectbdigroup5.populating.model.FestivalOpenData;
import com.bdi.projectbdigroup5.populating.model.LieuCovoiturageOpenData;
import com.bdi.projectbdigroup5.service.*;
import com.poiji.bind.Poiji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/openData")
public class openDataController {

    private List<FestivalOpenData> od = new ArrayList<>();
    private List<LieuCovoiturageOpenData> lc = new ArrayList<>();
    private List<CommuneDepartementRegionDataGouv> dataGouv = new ArrayList<>();

    @Autowired
    private RegionService regionService;

    @Autowired
    private DomainePrincipalService domainePrincipalService;

    @Autowired
    private DepartementService departementService;

    @Autowired
    private CommuneService communeService;

    @Autowired
    private LieuCovoiturageService lieuCovoiturageService;

    @Autowired
    private SousDomaineService sousDomaineService;

    @GetMapping("/")
    public List<FestivalOpenData> excelToList(@RequestParam String fileLocation) {
        od = Poiji.fromExcel(new File(fileLocation), FestivalOpenData.class);
        lc = Poiji.fromExcel(new File(fileLocation), LieuCovoiturageOpenData.class);

        return od;
    }

    @GetMapping("/dataGouv")
    public List<CommuneDepartementRegionDataGouv> dataGouvToList(@RequestParam String fileLocation) {
        dataGouv = Poiji.fromExcel(new File(fileLocation), CommuneDepartementRegionDataGouv.class);

        return dataGouv;
    }

    @GetMapping("domainePrincipals")
    public Iterable<DomainePrincipal> getDomainePrincipals() {
        Set<DomainePrincipal> domaines = new HashSet<>();
        od.forEach(f -> {
            DomainePrincipal domaine = new DomainePrincipal();
            domaine.setNom(f.getDomaine());
            domaines.add(domaine);
        });

        return domainePrincipalService.createDomainePrincipals(domaines);
    }

    @GetMapping("sousDomaines")
    public Iterable<SousDomaine> getSousDomaines() {
        Set<SousDomaine> domaines = new HashSet<>();
        od.forEach(f -> {
            String nom = f.getSousDomaine();

            SousDomaine sd = new SousDomaine();
            sd.setNom(nom);

            Optional<DomainePrincipal> dp = domainePrincipalService.findById(f.getDomaine());

            dp.ifPresent(d -> {
                if(sd.getNom() == null || sd.getNom().isEmpty()) sd.setNom(d.getNom());
                sd.setDomainePrincipal(d);
            });

            domaines.add(sd);
        });

        return sousDomaineService.createSousdomaines(domaines);
    }

    @GetMapping("regions")
    public Iterable<Region> getRegions() {
        Set<Region> data = new HashSet<>();

        dataGouv.forEach(f -> {
            if(f.getNomRegion() != null && !f.getNomRegion().isEmpty()) {
                Region r = new Region();
                r.setNom(f.getNomRegion());
                data.add(r);
                System.out.println(r.getNom() + "-> " + f.getNomRegion());
            }
        });

        return regionService.createRegions(data);
    }

    @GetMapping("departements")
    public Iterable<Departement> getDepartements() {
        Set<Departement> data = new HashSet<>();
        dataGouv.forEach(f -> {

            if(f.getCodeDepartement() != null && !f.getCodeDepartement().isEmpty() && f.getNomRegion() != null && !f.getNomRegion().isEmpty()) {
                Departement d = new Departement();
                d.setNumero(f.getCodeDepartement());
                d.setNom(f.getNomDepartement());

                System.out.println(f.getCodeDepartement() + "->" + d.getNumero());
                Optional<Region> region = regionService.findById(f.getNomRegion());
                region.ifPresent(d::setRegion);
                data.add(d);
            }
        });

        return departementService.createDepartements(data);
    }

    @GetMapping("communes")
    public Iterable<Commune> getCommunes() {
        Set<Commune> data = new HashSet<>();
        dataGouv.forEach(f -> {
            if(f.getCodeCommuneINSEE() != null && !f.getCodeCommuneINSEE().isEmpty() && f.getCodeDepartement() != null && !f.getCodeDepartement().isEmpty()) {
                Commune d = new Commune();
                d.setCodeInsee(f.getCodeCommuneINSEE());
                d.setNom(f.getNomCommuneComplet());
                d.setCodePostal(f.getCodePostal());
                d.setLatitude(f.getLatitude());
                d.setLongitude(f.getLongitude());

                System.out.println(f.getCodeDepartement());
                Optional<Departement> departement = departementService.findById(f.getCodeDepartement());
                departement.ifPresent(d::setDepartement);

                if(d.getDepartement() != null) {
                    data.add(d);
                }
            }
        });

        return communeService.createCommunes(data);
    }

    @GetMapping("festivals")
    public Iterable<Festival> getFestivals() {
        Set<Festival> data = new HashSet<>();
        od.forEach(f -> {
            Festival d = new Festival();
            d.setNom(f.getNomManifestation());
            d.setDateDebut(f.getDateDebut());
            d.setDateFin(f.getDateFin());
            d.setTarifPass(f.getTarifPass());
            d.setSiteWeb(f.getSiteWEB());

            data.add(d);
        });

        return data;
    }

    @GetMapping("lieuCovoiturages")
    public Iterable<LieuCovoiturage> getLieuCovoiturages() {

        final Map<String, TypeLieuCovoiturage> typeLieuMap = new HashMap<>();
        typeLieuMap.put("Supermarché", TypeLieuCovoiturage.SUPERMARCHE);
        typeLieuMap.put("Parking relais", TypeLieuCovoiturage.PARKING_RELAIS);
        typeLieuMap.put("Délaissé routier", TypeLieuCovoiturage.DELAISSE_ROUTIER);
        typeLieuMap.put("Aire de covoiturage", TypeLieuCovoiturage.AIRE_COVOITURAGE);
        typeLieuMap.put("Auto-stop", TypeLieuCovoiturage.AUTO_STOP);
        typeLieuMap.put("Sortie d'autoroute", TypeLieuCovoiturage.SORTIE_AUTOROUTE);
        typeLieuMap.put("Parking", TypeLieuCovoiturage.PARKING);

        Set<LieuCovoiturage> data = new HashSet<>();
        Set<String> typeLieu = new HashSet<>(); // utile pour voir les différents typeLieu

        lc.forEach(f -> {
           LieuCovoiturage d = new LieuCovoiturage();
            d.setNom(f.getNomLieu());
            d.setAdresse(f.getAdresseLieu());
            d.setLatitude(f.getLatitude());
            d.setLongitude(f.getLongitude());
            d.setType(typeLieuMap.get(f.getTypeLieu()));

            try {
                Optional<Commune> commune = communeService.findCommuneById(f.getCodeINSEELieu());
                commune.ifPresent(d::setCommune);  
            } catch (IllegalArgumentException e) {
                System.out.println(f.toString());
            }


            if(d.getCommune() != null) {
                data.add(d);
            }
            typeLieu.add(f.getTypeLieu());
        });

        return lieuCovoiturageService.createLieuCovoiturages(data);
    }
}
