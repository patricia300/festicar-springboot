package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.model.*;
import com.poiji.bind.Poiji;
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

    @GetMapping("/")
    public List<FestivalOpenData> excelToList(@RequestParam String fileLocation) {
        od = Poiji.fromExcel(new File(fileLocation), FestivalOpenData.class);
        lc = Poiji.fromExcel(new File(fileLocation), LieuCovoiturageOpenData.class);

        return od;
    }

    @GetMapping("domainePrincipals")
    public Iterable<Domaine> getDomainePrincipals() {
        Set<Domaine> domaines = new HashSet<>();
        od.forEach(f -> {
            DomainePrincipal domaine = new DomainePrincipal();
            domaine.setNom(f.getDomaine());
            domaines.add(domaine);
        });

        return domaines;
    }

    @GetMapping("sousDomaines")
    public Iterable<Domaine> getSousDomaines() {
        Set<Domaine> domaines = new HashSet<>();
        od.forEach(f -> {
            DomainePrincipal domaine = new DomainePrincipal();
            domaine.setNom(f.getSousDomaine());
            domaines.add(domaine);
        });

        return domaines;
    }

    @GetMapping("regions")
    public Iterable<Region> getRegions() {
        Set<Region> data = new HashSet<>();
        od.forEach(f -> {
            Region r = new Region();
            r.setNom(f.getRegion());
            data.add(r);
        });

        return data;
    }

    @GetMapping("departements")
    public Iterable<Departement> getDepartements() {
        Set<Departement> data = new HashSet<>();
        od.forEach(f -> {
            Departement d = new Departement();
            d.setNumero(f.getDepartement());
            d.setNom(f.getDepartement2());

            data.add(d);
        });

        return data;
    }

    @GetMapping("communes")
    public Iterable<Commune> getCommunes() {
        Set<Commune> data = new HashSet<>();
        od.forEach(f -> {
            Commune d = new Commune();
            d.setCodeInsee(f.getCodeINSEE());
            d.setNom(f.getCommuneINSEE());
            d.setCodePostal(f.getCodePostal());

            data.add(d);
        });

        /*lc.forEach(f -> {
            Commune d = new Commune();
            d.setCodeInsee(f.getCodeINSEELieu());
            d.setNom(f.getCommuneLieu());
            d.setLatitude(f.getLatitude());
            d.setLongitude(f.getLongitude());
        });*/

        return data;
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

            data.add(d);
            typeLieu.add(f.getTypeLieu());
        });

        return data;
    }
}
