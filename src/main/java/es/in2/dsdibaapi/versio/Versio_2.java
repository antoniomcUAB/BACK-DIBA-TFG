package es.in2.dsdibaapi.versio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Criteri;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.model.FrequenciaGravetat;
import es.in2.dsdibaapi.model.Gravetat;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.CriteriService;
import es.in2.dsdibaapi.service.EntornService;
import es.in2.dsdibaapi.service.FactorEconomicService;
import es.in2.dsdibaapi.service.FactorService;
import es.in2.dsdibaapi.service.FrequenciaGravetatService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.SituacioSocialService;
import es.in2.dsdibaapi.service.VersioModelService;
import es.in2.dsdibaapi.service.impl.AmbitServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(name = "api.db-init", havingValue = "v2")
public class Versio_2 implements CommandLineRunner {
		
	
	
	@Autowired
	private AmbitServiceImpl ambitService; 
	
	@Autowired
	private RiscService riscService;
	
	@Autowired
	private FrequenciaService frequenciaService;
	
	@Autowired
	private GravetatService gravetatService;
	
	@Autowired
	private EntornService entornService;
	
	@Autowired
	private SituacioSocialService situacioSocialService;
	
	@Autowired
	private FrequenciaGravetatService frequenciaGravetatService;
	
	@Autowired
	private CriteriService criteriService;
	
	@Autowired
	private FactorService factorService;	
	
	@Autowired
	private FactorEconomicService factorEconomicService;
	

	@Autowired
	private VersioModelService versioModelService;
	
	@Override
	public void run(String... args) throws Exception {
		
		VersioModel versio = versioModelService.save(VersioModel.builder()
				.versio("2")
				.data(new Date()).build());

		Frequencia ocasionalFrequencia= frequenciaService.findByDescription(FrequenciaService.Tipus.OCASIONAL.toString());
		Frequencia frequentFrequencia=frequenciaService.findByDescription(FrequenciaService.Tipus.FREQUENT.toString());
		Frequencia continuaFrequencia=frequenciaService.findByDescription(FrequenciaService.Tipus.CONTINUA.toString());
		Frequencia puntualFrequencia=frequenciaService.findByDescription(FrequenciaService.Tipus.PUNTUAL.toString());
		Frequencia senseFrequencia=frequenciaService.findByDescription(FrequenciaService.Tipus.SENSE_VALORACIO.toString());		
		
				
		Risc vulnerabilitatRisc = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
		Risc riscRisc = riscService.findByDescription(RiscService.Tipus.RISC);
		Risc alttRisc = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
		
		Gravetat baixaGravetat = gravetatService.findByDescription(GravetatService.Tipus.BAIXA.toString());		
		Gravetat moderadaGravetat= gravetatService.findByDescription(GravetatService.Tipus.MODERADA.toString());
		Gravetat  altaGravetat=gravetatService.findByDescription(GravetatService.Tipus.ALTA.toString());
		Gravetat proteccioGravetat=gravetatService.findByDescription(GravetatService.Tipus.PROTECCIO.toString());
		Gravetat riscGravetat=gravetatService.findByDescription(GravetatService.Tipus.RISC.toString());
		
		
		Ambit ambit = ambitService.save (Ambit.builder().versioModel(versio).versioModel(versio).descripcio("Autonomia").vulnerabilitat(4d).risc(7d).valVulnerabilitat(1d).valRisc(2.1d).valAltrisc(5d).build());
		
		Ambit ambitAutonomia = ambit;
		
		Entorn entornAutonomia = entornService.save (Entorn.builder().descripcio("Autonomia").ambit(ambit).build());
		
		
		
		SituacioSocial situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca d'autonomia personal a les  activitats bàsiques de la vida diaria  (A.1)",
																	"Situació en qué la persona no pot desenvolupar les activitats bàsiques de la vida diaria (alimentació, higiene, seguiment médic i farmacològic, mobilitat personal, descans, etc.), independentment de la causa que la motiva: malaltia física o psíquica, trastorn mental, deteriorament cognitiu, discapacitat física, psíquica,  intel·lectual o sensorial, etc.",entornAutonomia, 1d,4d,7d));
		
		
		
		
		FrequenciaGravetat frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenció/supervisió en algun moment puntual de la setmana/dia",baixaGravetat));
		
		criteriService.save(new Criteri ("Pérdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Crónica, sense possiblitats de millora",continuaFrequencia,riscRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenció/supervisió per les activitats de la vida diéria",moderadaGravetat)); 
		
		criteriService.save(new Criteri ("Pérdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Crónica, sense possiblitats de millora",continuaFrequencia,alttRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatService.save(
				new FrequenciaGravetat(situacioSocial,
						"Necessita atenció/supervisió continua les 24h",
						moderadaGravetat)); 
		
		criteriService.save(new Criteri ("Pérdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Crónica, sense possiblitats de millora",continuaFrequencia,alttRisc,frequenciaGravetat));
		
		
		situacioSocial = situacioSocialService.save(
		new SituacioSocial (versio,"Manca d’autonomia personal a les  activitats instrumentals de la vida diaria (A.2)",
							"Situació en què la persona no pot desenvolupar les activitats instrumentals de la vida diaria (utilització del transport públic, realització de tasques domèstiques, responsabilitat sobre la medicació, administració de ",
							entornAutonomia,0.75d,3d,6d));
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Necessita atenció/supervisió puntual en algun moment del dia/setmana",
		baixaGravetat)); 
		
		criteriService.save(new Criteri ("Pérdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Crónica, sense possiblitats de millora",continuaFrequencia,riscRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenció/supervisió per les activitats de la vida diéria",moderadaGravetat));
		
		
		criteriService.save(new Criteri ("Pérdua sobtada per causes sobrevingudes i possiblitats de millora",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Crónica, sense possiblitats de millora",puntualFrequencia,alttRisc,frequenciaGravetat));
		
		
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Necessita atenció/supervisió continua les 24h",altaGravetat));		
		
		criteriService.save(new Criteri ("Pérdua sobtada per causes sobrevingudes i possiblitats de millora",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Crónica, sense possiblitats de millora",puntualFrequencia,alttRisc,frequenciaGravetat));						
		
		
		ambit = ambitService.save(Ambit.builder().versioModel(versio).versioModel(versio).descripcio("MATERIAL I INSTRUMENTAL").vulnerabilitat(3d).risc(7d).valVulnerabilitat(0.5d).valRisc(1d).valAltrisc(2.5d).build());
		
		Ambit ambitMaterial = ambit;
		
		Entorn entornHabitatge = entornService.save (Entorn.builder().descripcio("Entorn habitatge").ambit(ambit).build());
			
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca d'habitatge (H.1)",
		"Situacions de manca d'un habitatge estable on fixar la residència. Inclou persones en situacions de _\"sense sostre\" o \"sense habitatge\" de les categories ETHOS i inclou les següents: Persona sense sostre, transeünt i/o vivint al carrer, persona allotjada temporalment en centres sociosantaris, penitienciaris, d'estrangeria o vinculats als serveis socials, i persones vivint en cases de familiars o amics, persones vivint en llocs no destinats a aquest efecte o que no es consideren habitatge)."
									,entornHabitatge,1d,2d,7d));
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenció/supervisió en algun moment puntual de la setmana/dia",moderadaGravetat));
		criteriService.save(new Criteri ("Sobrevinguda",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més d'un cop a l'any",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Sense sostre",puntualFrequencia,alttRisc,frequenciaGravetat));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense xarxa de suport ni possiblitat d'activar recurs d'habitage. Amb xarxa de suport o habitatge temporal d'urgéncia i preséncia de menors o dependents a cérrec",altaGravetat));
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
			
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Habitatge deficient (H.2)","Situacions que es corresponen a la categoria ETHOS habitatge inadequat i inclou domicili que no reuneix les condicions adequades d'habitabilitat o les necesséries per garantir l'autonémia i qualitat de vida de les persones que hi habiten. Inclou infrahabitatge (caravana i barraquisme). Inclou les situacions relacionades amb manca d'higiene/neteja greus."
							,entornHabitatge,0.2d,0.4d,4d));
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense risc en  la integritat física de les persones que hi habiten.",moderadaGravetat));
		criteriService.save(new Criteri ("Situacions excepcionals i restaruables",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Situacions estructurals ",puntualFrequencia,riscRisc,frequenciaGravetat));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Existeix risc en la integritat física de les persones que hi habiten ",altaGravetat));
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
			
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Habitatge insegur (H.3)","	",entornHabitatge,0.2d,0.4d,0.6d));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació impagament prévia a l'inici del procés judicial. Ocupacié il·legall",baixaGravetat));
		criteriService.save(new Criteri ("situació puntual i capcitat de resolucié",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Inferior 3 mesos consecutius",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més de 3 mesos. Endeutament creixent",puntualFrequencia,riscRisc,frequenciaGravetat));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Procés judicial iniciat",moderadaGravetat));
		criteriService.save(new Criteri ("Capacitat mediacié. Moratéria",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("En procés judicial sense capacitat mediacié. 	Moratéria",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("En subhasta",puntualFrequencia,alttRisc,frequenciaGravetat));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Senténcia amb ordre de llanéament",altaGravetat));
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
			
			
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Habitatge massificat (H.4)",
		"Situació que es troba a la categoria ETHOS d'habitatge inadequat i es defineix per una relació inadequada entre el nombre de persones que conviuen en un domicili i la superfície de l'habitatge, que difculta o no permet satisfer les activitats de la vida diària de forma satisfactòria."
									,entornHabitatge,0.2d,0.4d,0.6d));
			
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Un sol nucli familiar en espai reduét. Persones joves que comparteixen espais",baixaGravetat));
		
		criteriService.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Fa més de 3 mesos",puntualFrequencia,riscRisc,frequenciaGravetat));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"éInvasié familiaré: existeix un nucli familiar i arriben de forma sobtada nous membres.",moderadaGravetat));
		criteriService.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Fa més de 3 mesos",puntualFrequencia,riscRisc,frequenciaGravetat));
			
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Diverses families amb menors o dependents compartint espais. Situacions de relloguers sense contracte.",altaGravetat));
		criteriService.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Fa més de 3 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));
			
			
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Risc de pérdua o manca serveis/subministraments (H.5)",
		"Situació de risc de perdua o manca de subministraments (gas, aigua, electricitat), manca de parament bàsic o estris domèstics imprescindibles per satisfer les necessitats bàsiques de la unitat familiar. Inclou els risc de perdua per connexió il·legal."
								,entornHabitatge,0.2d,0.4d,0.6d));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Avés de tall. Manca de subministrament i és possible el restabliment",baixaGravetat));
		criteriService.save(new Criteri ("Primer cop",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Segon cop durant l'any",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més de 2 cops durant any",puntualFrequencia,riscRisc,frequenciaGravetat));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Avés de tall i manca de més d'un subministrament però és possible el restabliment",moderadaGravetat));
		criteriService.save(new Criteri ("Primer cop",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Segon cop durant l'any",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més de 2 cops durant any",puntualFrequencia,riscRisc,frequenciaGravetat));
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"No es pot restablir el servei",altaGravetat));
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
		
		
		Entorn entornEconomic = entornService.save (Entorn.builder().descripcio("Entorn Económic").ambit(ambit).build());
		
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Sense ingressos (E.1)",
		"Situació d'una unitat familiar o ed conivència que no té ingressos,o que no els acredita, o que no els té de forma estable o regular. Es comptabilitzaran en aquesta categoria aquelles unitats familiars on s’han identificat 7 o més ítems de l'inventari de carència material"
							,entornEconomic,1d,2d,3d));
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Disposen de xarxa o recursos que proporcionen  suport econémic. No han d'afrontar pagament de l'habitatge habitual",moderadaGravetat));
		criteriService.save(new Criteri ("De forma puntual/sobrevinguda i amb capacitat de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma continua/Crónica",puntualFrequencia,alttRisc,frequenciaGravetat));	
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"No disposen de xarxa o recursos de suport econémic",altaGravetat));	
				
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
		
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Ingressos insuficients (E.2)",
		"Situació d'una unitat familiar o de convivència que malgrat tenir uns ingressos estables o regulars té dificultats per cobrir determinades necessitats dels seus integrants. Es comptabilitzaran en aquesta categoria aquelles unitats familiars o de conviència on s'han identificat de 3 a 6 ítems de l'inventari de carència material."
							,entornEconomic,1d,2d,3d));
		
		versio.setPreguntaEconomica(situacioSocial.getId());
		versioModelService.save(versio);
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"3-4 étems del qüestionari privacié econémica i amb xarxa/recursos de suport econémic",baixaGravetat));	
				
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));	
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"3-4 étems del qüestionari sense xarxa de suport o. 5-6 amb xarxa familiar de suport que proporciona els recursos",moderadaGravetat));	
		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));						
			
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"5 o més étems del qüestionari sense xarxa/recursos de suport econémic",altaGravetat));
				
		criteriService.save(new Criteri ("De forma puntual/sobrevinguda i amb capacitat de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma continua i poca capacitat de canvi",puntualFrequencia,alttRisc,frequenciaGravetat));	
		
		
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Administració deficient dels ingressos (E.3)",
		"Situació d'una unitat familiar o de conviència que presenta dificultats per administrar el pressupost  i/o no prioritzar la cobertura de les necessitats bàsiques."
								,entornEconomic,0.2d,0.4d,0.6d));
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"No saben fer front a despeses extres inesperades",baixaGravetat));
		criteriService.save(new Criteri ("Situacions concretes  i capacitat de canvi",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma continua",puntualFrequencia,riscRisc,frequenciaGravetat));	
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Degut a la mala gestié tenen dificultats per cobrir les despeses bàsiques de la unitat familiar ",moderadaGravetat));
		criteriService.save(new Criteri ("Situacions concretes  i capacitat de canvi",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma continua",puntualFrequencia,alttRisc,frequenciaGravetat));						
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"La mala gestié és créncia i amb greus afectacions a la véda diéria",altaGravetat));	
				
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
					
		Entorn entornLaboral = entornService.save (Entorn.builder().descripcio("Entorn Laboral").ambit(ambit).build());
						
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca de qualificacié i/o d'orientació laboral  (L.1)",
		"Situació d'una persona que té dificultats per trobar una ocupació estable perquè desconeix els mecanismes per accedir al mercat laboral i no disposa de la formació i qualificació suficient per trobar una ocupació estable. En conseqüència, entra i surt del mercat laboral, realitza treballs que no donen continuïtat a la seva progressió professional o resta llargues temporades a l'atur.  Addicionalment, la persona pot tenir dificultats personals o legals que dificulten l'accés al mercat de treball o el manteniment d'una  feina. "
							,entornLaboral,0.5d,1d,1.5d));		
												
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Voluntat de treballar però dificultat en l'accés al mercat de treball. Situacions sobrevingudes (salut, situació legal en trémit, etc..)",baixaGravetat));		
		criteriService.save(new Criteri ("Ha treballat en el darrer any",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Ha treballat 6 mesos dels darrers 3 anys",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Atur de llarga durada/ fa més de 3 anys que no treballa",puntualFrequencia,riscRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Les dificultats en el manteniment de les carregues familiars i la cura agreujen la situació ",altaGravetat));		
		criteriService.save(new Criteri ("Ha treballat en el darrer any",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Ha treballat 6 mesos dels darrers 3 anys",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Atur de llarga durada/ fa més de 3 anys que no treballa",puntualFrequencia,alttRisc,frequenciaGravetat));	
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca de competéncies professionals transversals  (L.2)","La persona desconeix o no té les competéncies professionals necesséries per accedir al mercat laboral i mantenir una feina. En conseqééncia, entra i surt del mercat laboral per motius que tenen a veure amb l'actitud i la responsabilitat cap a la feina (inpuntualitat, manca de respecte als companys/es de feina o superior jerérquic, imatge personal no adaptada als requeriments de la feina, manca d'anticipacié de problemétiques, etc)."
							,entornLaboral,0.5d,1d,1d));		
												
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Consciéncia de canvi i voluntat de millora",baixaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));								
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Impossibilitat de canvi o millora a mig termini (1 any)",altaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));	
						
						
		ambit = ambitService.save(Ambit.builder().versioModel(versio).versioModel(versio).descripcio("RELACIONAL").vulnerabilitat(3d).risc(7d).valVulnerabilitat(1d).valRisc(2.1d).valAltrisc(5d).build());
		Ambit ambitRelacional = ambit;
						
		Entorn entornEscolar = entornService.save (Entorn.builder().descripcio("Entorn Escolar").ambit(ambit).build());
						
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Dificultats d'aprenentatge escolar (ES.1)",
		"Situació d'un infant o adolescent en edat d'escolarització obligatòria que presenta dificultats en el seguiment educatiu que li correspon com procés evolutiu, com a conseqüència de dinàmiques familiars adverses (inclouen desvinculació del centre educatiu, manca de recolzament, etc.)."
							,entornEscolar,0.75d,1.5d,3d));		
												
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Dificultats associades a les capacitats de l'infant, identificades i existeix una intervencié conjunta per part dels diferents serveis. Situacions puntuals que afecten a la seva escolaritat",baixaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervencié conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"familia que entorpeix la intervencié social ni accepta el treball. Afectacions molt greus en el desenvolupament integral de l'infant o adolescent",altaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca de competéncies relacionals a l'escola (ES.2)",
		"Situació d'un infant o adolescent en edat d'escolarització obligatòria que no s'adapta al sistema educatiu i que presenta conductes disruptives al centre, amb els adults i/o amb els seus iguals."
								,entornEscolar,0.75d,1.5d,3d));		
												
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial," Dificultats identificades i existeix una intervencié conjunta per part dels diferents serveis",baixaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervencié conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"familia que entorpeix la intervencié social ni accepta el treball. Afectacions molt greus en el desenvolupament integral de l'infant o adolesecent. Comportaments agressius envers companys o mestres",altaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Escolaritat obligatéria inacabada (ES.3)",
		"Situació d'un infant o adolescent en edat d'escolaritazció obligatòria que no està matriculat en cap centre, o que ha abandonat els estudis abans de la seva finalització.",entornEscolar,0.75d,1.5d,3d));		
												
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació puntual que afecta a la tramitacié de la seva escolaritat però amb voluntat i capacitat de resolucié en un curt termini (mateix curs escolar)",baixaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació amb marge de millora que requereix una intervencié conunta  dels diferents serveis i recursos especialitzats.",moderadaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial," No hi ha voluntat de canvi ni millora, La familia incentiva o no col·labora per resoldre la situació. Afectacions molt greus en el desenvolupament integral de l'infant o adolesecent. Comportaments agressius envers companys o mestres",altaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Absentisme escolar (ES.4)",
		"Situació d'infant o adolescent en edat d'escolarització obligatòria que no assisteix al centre al qual està matriculat, de forma reiterada o freqüent  i no justificada, ja sigui per pròpia voluntat o per voluntat de la família, però sense implicar abandonament dels estudis abans de la seva finalització.",entornEscolar,0.75d,1.5d,3d));		
												
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació puntaul per causa sobrevenguida (Malaltia o motius de salut/ familiars)",baixaGravetat));				
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));	
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervencié conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));		
		criteriService.save(new Criteri ("De 2 a 5 vegades al mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més de 5 vegades al mes",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("No assiteix més de 5 dies consecutius",puntualFrequencia,riscRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"La familia incentiva o no col·labora per resoldre l'absentisme",altaGravetat));
		
		
		// Nova version no es valora
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Assetjament escolar (ES.5)",
		"Situació d'una persona menor d'edat escolaritzat que està patint a situacions habituals de violència física i psíquica/emocional en el marc de l’escola, o que s’ha iniciat en aquest marc encara que les actuacions es produeixin fora, per part dels seus companys i companyes, ja sigui en l’entorn escolar o fora d’ell."
													,entornEscolar,0.75d,1.5d,3d));		
												
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial," S'identifiquen situacions de comentaris despectius provinents d'una sola persona.",baixaGravetat));					
		criteriService.save(new Criteri ("Fet puntual o en un periode de màxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,riscRisc,frequenciaGravetat));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"S'identifiquen  situacions d'agressions verbals per part de grup reduét",moderadaGravetat));					
		criteriService.save(new Criteri ("Fet puntual o en un periode de màxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,alttRisc,frequenciaGravetat));	
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Agressions físiques o agressions verbals provinents de gran grup",altaGravetat));					
		
		
		// Nova versió no es valora
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
						
		Entorn entornFamiliar = entornService.save (Entorn.builder().descripcio("Entorn Familiar").ambit(ambit).build());
		
						
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Negligència amb persones vulnerables (F.1)",
		"Situacions en les quals les necessitats bàsiques (físiques, socials, psicològiques) d'una persona vulnerable del nucli familiar (menor, depenent, gent gran , etc.) no són ateses, de manera temporal o permanent, per cap dels membres del grup on conviu. Implica l'omissió d'un deure o responsabilitat de cura envers les persones vulnerables del nucli familiar. "
								,entornFamiliar,1000d,1.5d,1000d));		
						
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense perill fésic i l'alumnatafectacié a nivell pséquic/emocional no interfereix en les competéncies bàsiques de la persona (autocura, comunicacié, autonomia i iniciativa)",moderadaGravetat));					
		
		// Nova versió no es valora
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Afectacions greu per la integritat de la persona",altaGravetat));			
		
		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
		
						
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Maltractament físic i/o emocional (F.2)",
		"Situació d'un menor d'edat, adult o persona gran que comporta vexacions de caràcter físic i/o psíquic/psicològic/emocional, així com les situacions de violència de gènere. Inclou la sospita per part del professional o bé l'evidència que el maltractament s'està produint.(Es consideren incloses en quest indicador totes les categories de maltractament relacionades al RUDEL)",
		entornFamiliar,1000d,1.5d,1000d));		
		
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense perill físic i l’afectació a nivell psíquic/emocional no interfereix en les competències bàsiques de la persona (autocura, comunicació, autonomia i iniciativa)",moderadaGravetat));					
		
		criteriService.save(new Criteri ("1 cop a l'any/ vida",ocasionalFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més d'un cop en el darrer any",frequentFrequencia,riscRisc,frequenciaGravetat));			
		criteriService.save(new Criteri ("Més d'un cop en el darrer mes",continuaFrequencia,alttRisc,frequenciaGravetat));
		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Maltractament físic o amb greus afectacions per la integritat de la persona",altaGravetat));			
		
		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));			
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Abús Sexual (F.3)",
		"La participació d'un/a menor d'edat, adult o persona gran, en activitats sexuals que no està en condicions de comprendre, que són inapropiades per a la seva edat i el seu desenvolupament psicosexual, per a les quals és incapaç de donar el seu consentiment i que transgredeixen els tabús i les regles familiars i socials, així com les lleis vigents. En l’abús sexual existeixen dos factors clau: la coerció i la asimetria d’edat o de moment evolutiu entre les persones implicades."
						,entornFamiliar,1000d,1.5d,1000d));		
						
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"En qualsevol situació",altaGravetat));			
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
											
		
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Dificultats per assumir responsabilitats familiars i socials (F.4)",
		"Situació de persones adultes que no són capaces d'assumir adequadament les seves responsabilitats en el si del nucli familiar, respecte de les persones que en depenen legalment",entornFamiliar,0.43d,0.77d,2.5d));		
						
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial," Manca de límits adequats a l'alumnat edat dels infants/adolescents/. No séinvolucra activament en el desenvolupament/cura de la persona. Un altre adult assumeix les responsabilitats.Voluntat de canvi i capacitat de millora",baixaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Manca de límits  o de cura de les persones que en depenen i ningé dins del nucli de convivéncia assumeix les responsabilitats. Voluntat de canvi i capacitat de millora",moderadaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));	
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"No hi ha acceptacié de la situació ni capacitat de millora a curt termini. Un/a menor assumeix les responsabilitats",altaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Estrés derivat de la situació familiar (F.5)",
		"Situació d'estrès derivada de canvis crÍtics en l'entorn familiar per situacions sobrevingudes, o com a conseqüència d'una sobrecàrrega motivada per les dinàmiques familiars o altres fets (abandonament de figures parentals, defuncions, canvis freqüents de domicili, pèrdua d'ingressos, manca continuada de treball, etc.).  Inclou risc de claudicació de cuidadors, si vénen motivats per un estat d'ansietat i sobrecàrrega.",entornFamiliar,0.43d,0.77d,2.5d));		
						
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Relacionat a fets concrets sobrevinguts.",baixaGravetat));		
											
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Instaurat en les dinàmiques familiars sense que  interfereixi en les activitats bàsiques.",moderadaGravetat));		
											
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Afectacions permanents en la cura i les responsabilitats familiars",altaGravetat));		
											
		criteriService.save(new Criteri ("Porta menys de 6 mesos",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Porta més de 6 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Sobre-responsabilitat (F.6)",
		"Situació d'un membre de la unitat familiar o de convivència, adult o menor d'edat, que assumeix responsabilitats familiars que es considera que es troben per sobre de les seves possibilitats. Aquí s'inclou l’embaràs adolescent. "
								,entornFamiliar,0.43d,0.77d,2.5d));		
						
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Relacionat a fets concrets sobrevinguts.",baixaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"La situació afecta a un adult amb mancances però amb capcitat de donar resposta a la situació mitjanéant el suport necessari. La situació afecta a un/a menor major de 16 anys, sense que afecti greument a la seva integritat piscològica i amb possiblitat de canvis i millora.",moderadaGravetat));		
											
		criteriService.save(new Criteri ("Menys de 2 mesos",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Entre 2 i 6 mesos",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Més de 6 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Afectacié greu a menors o persones dependents. La situació afecta a un adult que no la reconeix ni accepta el suport necessari.",altaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));				
											
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Dificultats en les relacions familiars (F.7)",
		"Situació d'una unitat familiar o de convivència que presenta dificultats de relació i comportaments agressius amb un o més membres de la unitat familiar o de convivència.",entornFamiliar,0.43d,0.77d,2.5d));		
						
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Fets molt concrets, amb reconeixement i voluntat de canvi. No instaurat en la dinémica familiar. Sense infants en el nucli de convivéncia.",baixaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Instaurat en la dinémica familiar però amb voluntat de canvi i millora. Amb infants en el nucli de convivéncia",moderadaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Afectacié greu en la dinémica familiar i la salut física/emocional dels seus membres",altaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));						
											
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,
		"Manca de recursos personals i autonomia en la presa de decisions(F.8)",
		"Situació en la qual una o més persones adultes de la unitat familiar o de convivència mostra incapacitat d'afrontar aspectes de la vida quotidiana en l'àmbit de l'organització familiar, econòmic,  laboral, de relació social, etc. No es consideren en aquest indicador les persones adultes en situació de dependència o discapacitat. ",entornFamiliar,0.43d,0.77d,2.5d));		
						
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació recent, motivada per fets externs i reversible amb  recursos personals",baixaGravetat));		
													
		criteriService.save(new Criteri ("Passa puntualment",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Passa de manera reiterada",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Continu",puntualFrequencia,riscRisc,frequenciaGravetat));							
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació  reversible amb suport extern",moderadaGravetat));		
													
		criteriService.save(new Criteri ("Passa puntualment",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Passa de manera reiterada",continuaFrequencia,riscRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Continu",puntualFrequencia,riscRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació irreversibale i amb necessitat de suport",altaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));				
												
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca d'higiene o desordre al domicili (F.9)",
		"Situació d'una unitat familiar o de convivència que no manté el seu habitatge en unes condiciones d'habitabilitat (higiene, ordre i seguretat) adequades per les persones que hi viuen. Inclou els casos en els quals la situació està vinculada a una maltia mental (Síndrome de Diògenes o de Noé).",entornFamiliar,0.43d,0.77d,2.5d));		
						
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació puntual i reversible amb el suport adequat. Persones diagnosticades que col·laboren.",baixaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació reiterada amb necessitat de suport extern. Persones diagnosticades que no col·laboren",moderadaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Preséncia de menors o dependents al domicili. Greus afectacions per la integritat física de les persones. Indécis de malatia mental no diagnosticada ni en tractament",altaGravetat));		
													
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));						
											
								
		
		Entorn entornSocial = entornService.save (Entorn.builder().descripcio("Entorn Social").ambit(ambit).build());
						
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,
		"Dificultats en les relacions socials i a la participacié a la comunitat (S.1)",
		"Situació d'una persona i/o unitat familiar o de convivència  amb dificultats per adaptar-se al seu medi social (aïllament, no respecta els límits normatius ni socials del seu entorn).",entornSocial,0.2d,0.5d,1.5d));		
									
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Existeix algun xarxa relacional de suport tot i que es detecten dificultats per crear o mantenir noves relacions. Participa en alguns entorns puntuals però presenta una manca déhabilitats i capacitats per participar a la comunitat",baixaGravetat));		
									
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Manca de motivacié i voluntat per relacionar-se amb l'entorn comunitari. Aéllament social. Comportaments agressius en l'entorn. Els contactes sén amb un entorn relacional téxic/negatiu",moderadaGravetat));		
											
		criteriService.save(new Criteri ("Puntual. Conjuntural amb possibilitats de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Continu",continuaFrequencia,riscRisc,frequenciaGravetat));			
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Rebuig social (S.2)",
		"Situació d'una persona i/o unitat familiar o de convivència socialment aïllada i/o rebutjada per raons físiques,  de gènere, ètnia, religió, orientació sexual, conducta o qualsevol altra raó.",entornSocial,0.2d,0.5d,1.5d));		
									
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Rebuig en un sol entorn i sense afectacions a la integritats física o emocional de la personal",moderadaGravetat));		
									
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"En més déun entorn. Amb afectacions a la integritat de la persona",altaGravetat));		
									
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));					
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Addiccions (S.3)",
		"Situació d'una persona que fa un ús abusiu d'alguna substància tòxica i/o que presenta conductes addictives (joc, noves tecnologies, etc.).",entornSocial,0.2d,0.5d,1.5d));		
									
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"En tractament i sense afectacions en les activitats essencials per la vida diéria ",baixaGravetat));		
												
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense tractar o No compliment de les pautes de tractament. Afectacions no interfereixen en les activitats essencials de la vida diéria o les responsabilitats familiars  ",moderadaGravetat));		
													
		criteriService.save(new Criteri ("De 1 a 3 vegades per setmana",continuaFrequencia,riscRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Més de 3 vegades per setmana",puntualFrequencia,alttRisc,frequenciaGravetat));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Abés sever i afectacié important en les activitats essencials de la vida diéria o les responsabilitats familiars",altaGravetat));		
												
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));				
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Desatenció institucional  (S.4)",
		"Situació d'una persona que per la seva situació legal o adminsitrativa no tenen accés a determinats recursos de l'àmbit de  salut, educació i protecció social.",entornSocial,0.25d,0.25d,1d));		
									
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense valoració",altaGravetat));		
									
		criteriService.save(new Criteri ("Capacitat de resolucié / legalitzacié",frequentFrequencia,riscRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Incapacitat de resolucié ",continuaFrequencia,alttRisc,frequenciaGravetat));		
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Activitats marginals (S.5)",
		"Situació d'una persona que realitza un treball no legalitzat i/o activitats marginals (prostitució, venda ambulant il·legal, recollida de ferralla, altres).",entornSocial,0.2d,0.5d,1.5d));		
									
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Treball estable, sense coercions ni situacions de risc greu per la persona. Contacte esporédic amb la policia i/o mesures penals pendent",moderadaGravetat));		
									
		criteriService.save(new Criteri ("Sense valoració",puntualFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Sense valoració",continuaFrequencia,riscRisc,frequenciaGravetat));			
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Les activitats marginals comporten un alt risc (salut física i emocianal, delictes penals) per la persona.",altaGravetat));		
												
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
		
		
		
		// Autonomia
		
		// Protecció
		
		factorService.save(Factor.builder().descripcio("La malaltia causa del dèficit d'autonomia està diagnosticada i en tractament")
				.gravetat(proteccioGravetat)
				.ambit(ambitAutonomia)
				.fc1m(-0.25d)
				.fctots(-0.5d)
				.build());
		factorService.save(Factor.builder().descripcio("La persona té reconeguts el grau de discapacitat i / o dependència")
		.gravetat(proteccioGravetat)
		.ambit(ambitAutonomia)
		.fc1m(-0.4d)
		.fctots(-0.8d)
		.build());
		factorService.save(Factor.builder().descripcio("La persona és usuària d'un servei, recurs o prestació  en funció de la seva malaltia, discapacitat o situació de dependència")
		.gravetat(proteccioGravetat)
		.ambit(ambitAutonomia)
		.fc1m(-0.5d)
		.fctots(-1d)
		.build());
		factorService.save(Factor.builder().descripcio("La persona té uns hàbits saludables (alimentació, salut etc.) per a la situació de la seva salut/Segueix tractament mèdic")
		.gravetat(proteccioGravetat)
		.ambit(ambitAutonomia)
		.fc1m(-0.25d)
		.fctots(-0.5d)
		.build());
		
		
		// Risc
		
		factorService.save(Factor.builder().descripcio("Es preveu que el dèficit d’autonomia de la persona s’agreugi en el curt termini (durant l’any) ")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.2d)
		.fctots(0.5d)
		.build());
		
		
		factorService.save(Factor.builder().descripcio("La malaltia causa del dèficit d'autonomia no està diagnosticada i en tractament")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.25d)
		.fctots(0.5d)
		.build());
		factorService.save(Factor.builder().descripcio("La persona no segueix amb regularitat el tractament mèdic prescrit")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.4d)
		.fctots(0.8d)
		.build());
		
		factorService.save(Factor.builder().descripcio("No existeix una xarxa familiar i / o social o no s'impliquen en la cura")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.5d)
		.build());
		
		factorService.save(Factor.builder().descripcio("Cuidador / a habitual en risc o en situació de claudicació")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.5d)
		.build());
		
		factorService.save(Factor.builder().descripcio("La situació i/o característiques del domicili agreugen el dèficit d’autonomia de les persones que hi viuen")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.5d)
		.build());
		
		factorService.save(Factor.builder().descripcio("La persona amb dèficit d’autonomia té responsabilitats/càrregues familiars")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.5d)
		.build());
		
		
		
		
		
		
		
		// Material
		
		// Protecció
		
		factorService.save(Factor.builder().descripcio("Una o més persones de la família disposa de treball estable.")
				.gravetat(proteccioGravetat)
				.ambit(ambitMaterial)
				.fc1m(-0.25d)
				.fctots(-0.5d)
				.build());
		factorService.save(Factor.builder().descripcio("Una o més persones de la família disposa d'ingressos estables (pensions, prestacions, rendes, etc.).")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.25d)
		.fctots(-0.5d)
		.build());
		factorService.save(Factor.builder().descripcio("Una o més persones de la família disposa d'estalvis")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.25d)
		.fctots(-0.5d)
		.build());
		factorService.save(Factor.builder().descripcio("Una o més persones de la família disposa de patrimoni a part de la primera residència")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.25d)
		.fctots(-0.5d)
		.build());
		
		
		factorService.save(Factor.builder().descripcio("La persona/família disposa d'una xarxa familiar/social diposada a donar suport econòmic")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.5d)
		.build());
		
		
		factorService.save(Factor.builder().descripcio("En cas de separació o divorci hi ha conveni regulador i es compleixen els seus termes")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.5d)
		.build());
		factorService.save(Factor.builder().descripcio("Es disposa d’habitatge social o d’un habitatge habitual en propietat i sense hipoteca pendent de pagament")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.5d)
		.build());
		
		// Risc
		
		factorService.save(Factor.builder().descripcio("Existeix un impagament de pensions de manutenció i la corresponent demanda judicial")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.5d)
		.fctots(0.5d)
		.build());
		
		factorService.save(Factor.builder().descripcio("Existeixen responsabilitats i càrregues familiars que agreugen la manca de recur4sos materials/instrumentals")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.25d)
		.build());
		
		factorService.save(Factor.builder().descripcio("Hi ha un endeutament creixent i inassolible amb els recursos disponibles")
		.gravetat(riscGravetat)
		.ambit(ambitAutonomia)
		.fc1m(0.25d)
		.build());
		
		
		// Relacional
		
		// Protecció
		
		factorService.save(Factor.builder().descripcio("La persona mostra una vinculació relacional positiva amb la família extensa o les seves xarxes d’amics i relacions")
				.gravetat(proteccioGravetat)
				.ambit(ambitRelacional)
				.fc1m(-0.1d)
				.fctots(-0.2d)
				.build());
		factorService.save(Factor.builder().descripcio("La persona i / o família està vinculada i participa activament a la comunitat, entitats o associacions dels seu entorn")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.1d)
		.fctots(-0.2d)
		.build());
		factorService.save(Factor.builder().descripcio("Existeix una xarxa familiar o relacional positiva disposada a oferir suport i acompanyament")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.5d)
		.build());
		factorService.save(Factor.builder().descripcio("La persona/família és usuària de recursos/serveis adreçats a cobrir la necessitat en l’àmbit relacional (escolar, familiar o comunitari)")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.25d)
		.fctots(-0.5d)
		.build());
		
		
		// Risc
		
		factorService.save(Factor.builder().descripcio("Sospita o malaltia mental diagnosticada que agreuja la situació")
		.gravetat(riscGravetat)
		.ambit(ambitMaterial)
		.fc1m(0.25d)
		.fctots(0.5d)
		.build());
		
		
		factorService.save(Factor.builder().descripcio("Existeix una xarxa social (veïns, amics, família extensa..) que exerceix una influència negativa ")
		.gravetat(riscGravetat)
		.ambit(ambitMaterial)
		.fc1m(0.5d)
		.build());
		factorService.save(Factor.builder().descripcio("Existeix una problemàtica de salut que agreuja greument la situació")
		.gravetat(riscGravetat)
		.ambit(ambitMaterial)
		.fc1m(0.5d)
		.build());
		
		// Infants
		
		// Protecció
		
		factorService.save(Factor.builder().descripcio("En el cas d'infància que el nen / a manté relació afectiva amb els adults de referència i iguals")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.25d)
		.fctots(-0.5d)
		.infants(true)
		.build());
		factorService.save(Factor.builder().descripcio("En el cas d'infància que el nen / a mostra una autonomia personal adequada per a la seva edat")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.25d)
		.fctots(-0.5d)
		.infants(true)
		.build());
		factorService.save(Factor.builder().descripcio("En el cas d'infància que el nen / a participa en activitats fora de l'entorn familiar o escolar")
		.gravetat(proteccioGravetat)
		.ambit(ambitMaterial)
		.fc1m(-0.25d)
		.fctots(-0.5d)
		.infants(true)
		.build());
		
		// Risc
		
		factorService.save(Factor.builder().descripcio("En el cas d’infància que el nen/a no manté una relació afectiva amb els iguals i els adults de referència")
		.gravetat(riscGravetat)
		.ambit(ambitMaterial)
		.fc1m(0.25d)
		.fctots(0.5d)
		.infants(true)
		.build());
		
		
		factorService.save(Factor.builder().descripcio("En el cas d’infància que el nen/a no mostra una autonomia i desenvolupament adequats a la seva edat")
		.gravetat(riscGravetat)
		.ambit(ambitMaterial)
		.fc1m(0.25d)
		.fctots(0.5d)
		.infants(true)
		.build());
		factorService.save(Factor.builder().descripcio("En el cas d’infància que el nen/a no participa en activitats fora de l’entorn escolar")
		.gravetat(riscGravetat)
		.ambit(ambitMaterial)
		.fc1m(0.25d)
		.fctots(0.5d)
		.infants(true)
		.build());
		
		
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Pagar les factures d'habitatge o serveis públics.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Mantenir la llar adequadament calenta.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Assumir despeses inesperades.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Menjar carn o les proteénes de forma regular.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Anar de vacances.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Disposar de cotxe/transport.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Disposar de rentadora.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Disposar de connexió a internet a la llar.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Disposar de teléfon móbil").build());
		
		
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Existeix una xarxa familiar/relacional disposada a oferir suport econòmic?").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
		.descripcio("Les dificultats en l'àmbit econòmic són contïnuades i sense possiblitat de canvi o millora en el curt termini?").build());
		
		ambit = ambitService.save(Ambit.builder().versioModel(versio).descripcio("Globalitat del cas").valVulnerabilitat(2.5d).valRisc(5d).build());
		
		Entorn entorn = entornService.save (Entorn.builder().descripcio("Globalitat del cas").ambit(ambit).build());
		
		
		// Protecció
		
		factorService.save(Factor.builder().descripcio("La persona i / o familia té consciencia i accepta  les dificultats / malaltia causants de la seva situació")
		.gravetat(proteccioGravetat)
		.ambit(ambit)
		.fc1m(-0.1d)
		.build());
		factorService.save(Factor.builder().descripcio("La persona i / o familia accepta el suport i l'orientació professional")
		.gravetat(proteccioGravetat)
		.ambit(ambit)
		.fc1m(-0.1d)
		.build());
		factorService.save(Factor.builder().descripcio("La persona coneix i té una actitud proactiva i participativa envers el sistema i les xarxes de protecció")
		.gravetat(proteccioGravetat)
		.ambit(ambit)
		.fc1m(-0.1d)
		.build());
		
		// Risc
		
		factorService.save(Factor.builder().descripcio("La persona i / o familia no té consciencia i/o no accepta  les dificultats/malaltia causants de la seva situació")
		.gravetat(riscGravetat)
		.ambit(ambit)
		.fc1m(0.1d)
		.build());
		
		factorService.save(Factor.builder().descripcio("La persona i / o familia no accepta el suport i l'orientació professional")
		.gravetat(riscGravetat)
		.ambit(ambit)
		.fc1m(0.1d)
		.build());
		
		
		factorService.save(Factor.builder().descripcio("La persona no té una actitud activa i ni participativa envers el sistema i les xarxes de protecció i/o té una posicionament victimista respecte els sistemes de protecció")
		.gravetat(riscGravetat)
		.ambit(ambit)
		.fc1m(0.1d)
		.build());
		factorService.save(Factor.builder().descripcio("Composicié familiar vulnerable (monoparental, families nombroses de categoria especial)")
		.gravetat(riscGravetat)
		.ambit(ambit)
		.fc1m(0.1d)
		.build());
		
		
	}
	
	
}
