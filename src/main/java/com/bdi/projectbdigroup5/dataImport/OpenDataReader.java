package com.bdi.projectbdigroup5.dataImport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.model.Departement;
import com.bdi.projectbdigroup5.model.DomainePrincipal;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.LieuCovoiturage;
import com.bdi.projectbdigroup5.model.Region;
import com.bdi.projectbdigroup5.model.SousDomaine;

import com.bdi.projectbdigroup5.repository.CommuneRepository;


public class OpenDataReader {

    private String filePath;
    @Autowired
    private CommuneRepository communeRepository;

    
    public OpenDataReader(String filePath){
        this.filePath=filePath;
    }

    public void importData() throws FileNotFoundException, IOException{

        FileInputStream file = new FileInputStream(filePath);
        Workbook workBook  = new XSSFWorkbook(file);

        Sheet festivalSheet = workBook.getSheet("Festivals");
        Sheet lieuCovoiturageSheet = workBook.getSheet("LieuxCovoiturage");

        importFestivalSheetData(festivalSheet);
        importLieuCovoiturageSheetData(lieuCovoiturageSheet);
        workBook.close();
    }

    public void importFestivalSheetData(Sheet festivalSheet){

        Iterator<Row> rows =  festivalSheet.iterator();

        Festival festival;
        Region region ;
        DomainePrincipal domainePrincipal;
        SousDomaine sousDomaine ;
        Departement departement ;
        Commune commune;


        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // skip header
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cellsInRow = currentRow.iterator();
            
            festival =new Festival();
            region =new Region();
            domainePrincipal=new DomainePrincipal();
            sousDomaine = new SousDomaine();
            departement = new Departement();
            commune = new Commune();

            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
            
                Cell currentCell = cellsInRow.next();

                switch (cellIdx) {
                    case 0:
                        festival.setNom(currentCell.getStringCellValue());
                        break;

                    case 1:
                        region.setNom(currentCell.getStringCellValue());
                        break;

                    case 2:
                        domainePrincipal.setNom(currentCell.getStringCellValue());
                        break;

                    case 3:
                        sousDomaine.setNom(currentCell.getStringCellValue());
                        break;
                    
                    case 4:
                        departement.setNumero((int)currentCell.getNumericCellValue()+"");
                        break;
                    
                    case 6:
                        festival.setSiteWeb(currentCell.getStringCellValue());
                        break;

                    case 7:
                        commune.setNom(currentCell.getStringCellValue());
                        break;

                    case 8:
                        commune.setCodePostal((int)currentCell.getNumericCellValue()+"");
                        break;

                    case 9:
                        commune.setCodeInsee(currentCell.getStringCellValue());
                        break;

                    case 10:
                        commune.setLongitude(Float.parseFloat(currentCell.getStringCellValue()));
                        break;

                    case 11:
                        commune.setLatitude(Float.parseFloat(currentCell.getStringCellValue()));
                        break;
                    
                    case 13:
                        departement.setNom(currentCell.getStringCellValue());
                        break;
                    
                    case 14:
                        festival.setDateDebut(currentCell.getDateCellValue());
                        break;

                    case 15:
                        festival.setDateFin(currentCell.getDateCellValue());
                        break;
                    
                    case 16:
                        festival.setTarifPass((float)currentCell.getNumericCellValue());
                        break;
                        
                    default:
                        break;
                }

                cellIdx++;
            }

            festival.setCommune(commune);
            festival.setDomainePrincipal(domainePrincipal);
            if (!sousDomaine.getNom().isEmpty())
                festival.setSousDomaine(sousDomaine);
            commune.setDepartement(departement);
            departement.setRegion(region);

        }
    }

    @Autowired
    public void importLieuCovoiturageSheetData(Sheet lieuCovoiturageSheet){

        Iterator<Row> rows =  lieuCovoiturageSheet.iterator();

        LieuCovoiturage lieuCovoiturage;


        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // skip header
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cellsInRow = currentRow.iterator();
            
            lieuCovoiturage = new LieuCovoiturage();

            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
            
                Cell currentCell = cellsInRow.next();

                switch (cellIdx) {
                    case 1:
                        lieuCovoiturage.setNom(currentCell.getStringCellValue());
                        break;
                    case 2:
                        lieuCovoiturage.setAdresse(currentCell.getStringCellValue());
                        break;
                    case 4:
                        Optional<Commune> result = communeRepository.findById(currentCell.getStringCellValue());
                        if(result.isPresent())
                            lieuCovoiturage.setCommune(result.get());
                        break;
                    case 6:
                        lieuCovoiturage.setLongitude(currentCell.getStringCellValue());
                        break;
                    case 7:
                        lieuCovoiturage.setLatitude(currentCell.getStringCellValue());
                        break;
                    default:
                        break;
                }

                cellIdx++;
            }

        }
    }

}
