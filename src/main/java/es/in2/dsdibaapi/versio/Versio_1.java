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
@ConditionalOnProperty(name = "api.db-init", havingValue = "v1")
public class Versio_1 implements CommandLineRunner {

	
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
											.versio("1")
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
		
		
		
		
		Ambit ambit = ambitService.save (
				Ambit.builder().descripcio("Autonomia")
				.vulnerabilitat(4d).risc(7d).valVulnerabilitat(1d).valRisc(2.1d)
				.valAltrisc(5d).versioModel(versio).build());
		
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
		
		
		ambit = ambitService.save(Ambit.builder().descripcio("MATERIAL I INSTRUMENTAL").versioModel(versio).vulnerabilitat(3d).risc(7d).valVulnerabilitat(0.5d).valRisc(1d).valAltrisc(2.5d).build());
		
		Ambit ambitMaterial = ambit;
				
		Entorn entornHabitatge = entornService.save (Entorn.builder().descripcio("Entorn habitatge").ambit(ambit).build());
								
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca d'habitatge (H.1)","Situacions de manca d'un habitatge estable on fixar la residéncia. Inclou persones en situacions de sense sostre o sense llar de les categories ETHOS i inclou persones acollides temporalment en una institucié a través de xarxa o en domicilis de familiars o amics persones que perden l'acolliment al domicili on vivien o que fixen el seu domicili en habitacles que no es consideren un habitatge (per exemple un cotxe)."
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
								
								
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Habitatge massificat (H.4)","situació d'habitatge massificat que es troba a la categoria ETHOS d'habitatge inadequat. Relacié inadequada entre el nombre de persones que conviuen en un domicili i la superfécie de l'habitatge, que no permet satisfer les activitats de la vida diéria de forma satisfactéria. / salubritat llei habitatge."
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
								
								
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Risc de pérdua o manca serveis/subministraments (H.5)","Risc de perdua o manca de subministraments (gas, aigua, electricitat), manca de parament bàsic o estris doméstics imprescindibles per satisfer les necessitats bàsiques de la unitat familiar. Inclou els risc de perdua per connexió il·legall."
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

		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Sense ingressos (E.1)","Unitats familiars que no tenen ingressos o que no els acrediten de forma estable o regular. Es proposa Comptabilitzar en aquesta categoria aquelles unitats familiars on séhan identificat més de 6 étems dels qüestionari."
												,entornEconomic,1d,2d,3d));
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Disposen de xarxa o recursos que proporcionen  suport econémic. No han déafrontar pagament de l'habitatge habitual",moderadaGravetat));
		criteriService.save(new Criteri ("De forma puntual/sobrevinguda i amb capacitat de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma continua/Crónica",puntualFrequencia,alttRisc,frequenciaGravetat));	
							
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"No disposen de xarxa o recursos de suport econémic",moderadaGravetat));	
									
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
							
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Ingressos insuficients (E.2)","Unitats familiars que malgrat tenir uns ingressos tenen dificultats per cobrir determinades necessitats  dels seus integrants."
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
							
							
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Administracié deficient (E.3)","Unitat familiar amb ingressos però que presenta dificultats per administrar el pressupost  i no prioritzar la cobertura de les necessitats bàsiques."
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
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca de qualificacié i/o d'orientació laboral  (L.1)","La persona té dificultats per trobar una feina estable perqué desconeix els mecanismes per accedir al mercat laboral i no disposa de la formacié i qualificacié suficient per trobar una feina estable. En conseqééncia, entra i surt del mercat laboral, realitza treballs que no donen continuétat a la seva progressié professional o resta llargues temporades a l'atur.  Addicionalment, la persona pot tenir dificultats personals o legals que dificulten l'accés al mercat de treball o el manteniment d'una  feina."
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
											
											
		ambit = ambitService.save(Ambit.builder().versioModel(versio).descripcio("RELACIONAL").vulnerabilitat(3d).risc(7d).valVulnerabilitat(1d).valRisc(2.1d).valAltrisc(5d).build());
		Ambit ambitRelacional = ambit;
											
		Entorn entornEscolar = entornService.save (Entorn.builder().descripcio("Entorn Escolar").ambit(ambit).build());
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Dificultats d'aprenentatge escolar (ES.1)","Infant o adolescent que presenta dificultats en el seguiment educatiu que li correspon com procés evolutiu, com a conseqééncia de dinémiques familiars adverses (inclouen desvinculacié del centre educatiu, manca de recolzament, etc.)."
												,entornEscolar,0.75d,1.5d,3d));		
																	
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Dificultats associades a les capacitats de l'infant, identificades i existeix una intervencié conjunta per part dels diferents serveis. Situacions puntuals que afecten a la seva escolaritat",baixaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervencié conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"familia que entorpeix la intervencié social ni accepta el treball. Afectacions molt greus en el desenvolupament integral de l'infant o adolescent",altaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca de competéncies relacionals a l'escola (ES.2)","Infant o adolescent que no s'adapta al sistema educatiu en l'etapa obligatéria i que presenta conductes disruptives al centre, amb els adults i/o amb els seus iguals."
													,entornEscolar,0.75d,1.5d,3d));		
																	
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial," Dificultats identificades i existeix una intervencié conjunta per part dels diferents serveis",baixaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervencié conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"familia que entorpeix la intervencié social ni accepta el treball. Afectacions molt greus en el desenvolupament integral de l'infant o adolesecent. Comportaments agressius envers companys o mestres",altaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Escolaritat obligatéria inacabada (ES.3)","Menor en edat d'escolaritzacié obligatéria que no esté matriculat en cap centre, o que ha abandonat els estudis abans de la seva finalitzacié.",entornEscolar,0.75d,1.5d,3d));		
																	
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació puntual que afecta a la tramitacié de la seva escolaritat però amb voluntat i capacitat de resolucié en un curt termini (mateix curs escolar)",baixaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació amb marge de millora que requereix una intervencié conunta  dels diferents serveis i recursos especialitzats.",moderadaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial," No hi ha voluntat de canvi ni millora, La familia incentiva o no col·labora per resoldre la situació. Afectacions molt greus en el desenvolupament integral de l'infant o adolesecent. Comportaments agressius envers companys o mestres",altaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Absentisme escolar (ES.4)","No assisténcia al centre en que esta matriculat, de forma reiterada i no justificada, de l'alumnatalumnat en edat escolar (dels 3 als 16 anys) ja sigui per prépia voluntat o per voluntat de la familia.",entornEscolar,0.75d,1.5d,3d));		
																	
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació puntaul per causa sobrevenguida (Malaltia o motius de salut/ familiars)",baixaGravetat));				
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervencié conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));		
		criteriService.save(new Criteri ("De 2 a 5 vegades al mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més de 5 vegades al mes",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("No assiteix més de 5 dies consecutius",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"La familia incentiva o no col·labora per resoldre l'absentisme",altaGravetat));		
		criteriService.save(new Criteri ("De 2 a 5 vegades al mes",frequentFrequencia,alttRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més de 5 vegades al mes",continuaFrequencia,alttRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("No assiteix més de 5 dies consecutius",puntualFrequencia,alttRisc,frequenciaGravetat));					
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Assetjament escolar (ES.5)","Infant o adolescent que esté patint Situacions habituals de violéncia física i psíquica en el marc de l'alumnatescola, o que séha iniciat en aquest marc encara que les actuacions es produeixin fora, per part dels seus companys i companyes, ja sigui en l'entorn escolar o fora déell"
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
		criteriService.save(new Criteri ("Fet puntual o en un periode de màxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,alttRisc,frequenciaGravetat));
											
		Entorn entornFamiliar = entornService.save (Entorn.builder().descripcio("Entorn Familiar").ambit(ambit).build());
				
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Maltractament (F.1)","Situacions de maltractament que rep un menor d'edat,  adult o  persona gran, inclou vexacions de carécter fésic, psicolégic o emocional, aixé com les situacions de violéncia de génere. Inclou la sospita per part del professional o bé l'evidéncia que el maltractament s'esté produint.(Es consideren incloses en quest indicador totes les categories de maltractament relacionades al RUDEL)"
													,entornFamiliar,1000d,1.5d,1000d));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense perill fésic i l'alumnatafectacié a nivell pséquic/emocional no interfereix en les competéncies bàsiques de la persona (autocura, comunicacié, autonomia i iniciativa)",moderadaGravetat));					
		criteriService.save(new Criteri ("1 cop a l'any/ vida",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Més d'un cop en el darrer any",continuaFrequencia,riscRisc,frequenciaGravetat));			
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Abés Sexual (F.2)","La participacié del nen/a i/o adolescent, adult o persona gran, en activitats sexuals que no esté en condicions de comprendre, que sén inapropiades per a la seva edat i el seu desenvolupament psicosexual, per a les quals és incapaé de donar el seu consentiment i que transgredeixen els tabés i les regles familiars i socials, aixé com les lleis vigents. En l'alumnatabés sexual existeixen dos factors clau: la coercié i la asimetria déedat o de moment evolutiu entre les persones implicades.",
				entornFamiliar,1000d,1000d,1000d));		
											
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"En qualsevol situació",altaGravetat));			
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));			
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Negligéncia amb persones vulnerables (F.3)","Situacions en les quals les necessitats bàsiques (físiques, socials, psicològiques) d'una persona vulnerable del nucli familiar (menor, depenent, gent gran , etc.) no sén ateses de manera temporal o permanent per cap dels membres del grup on conviu. Val a dir que situacions de negligéncia cronificada amb conseqééncies greus per a la salut i el benestar de la persona que els rep s'han de considerar una forma de maltractament."
											,entornFamiliar,0.77d,1.5d,2.5d));		
											
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense perill fésic i l'alumnatafectacié a nivell pséquic/emocional no interfereix en les competéncies bàsiques de la persona (autocura, comunicacié, autonomia i iniciativa).",moderadaGravetat));			
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Afectacions greu per la integritat de la persona",altaGravetat));					
		criteriService.save(new Criteri ("situació puntual",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("situació reiterada",puntualFrequencia,alttRisc,frequenciaGravetat));	
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Dificultats per assumir responsabilitats familiars i socials (F.4)","Persones adultes que no sén capaces d'assumir adequadament les seves responsabilitats en el si del nucli familiar, respecte de les persones que en depenen legalment",entornFamiliar,0.43d,0.77d,2.5d));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial," Manca de límits adequats a l'alumnat edat dels infants/adolescents/. No séinvolucra activament en el desenvolupament/cura de la persona. Un altre adult assumeix les responsabilitats.Voluntat de canvi i capacitat de millora",baixaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Manca de límits  o de cura de les persones que en depenen i ningé dins del nucli de convivéncia assumeix les responsabilitats. Voluntat de canvi i capacitat de millora",moderadaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"No hi ha acceptacié de la situació ni capacitat de millora a curt termini. Un/a menor assumeix les responsabilitats",altaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Estrés derivat de la situació familiar (F.5)","Situacions d'estrés derivades de canvis impactant en l'entorn familiar per situacions sobrevingudes, o com a conseqééncia d'una sobrecérrega motivada per les dinémiques familiars.  Inclou risc de claudicacié de cuidadors, si vénen motivats per un estat d'ansietat i sobrecérrega.",entornFamiliar,0.43d,0.77d,2.5d));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Relacionat a fets concrets sobrevinguts.",baixaGravetat));		
																
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Instaurat en les dinémiques familiars sense que  interfereixi en les activitats bàsiques.",moderadaGravetat));		
																
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Afectacions permanents en la cura i les responsabilitats familiars",altaGravetat));		
																
		criteriService.save(new Criteri ("Porta menys de 6 mesos",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Porta més de 6 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Sobre-responsabilitat (F.6)","Membre de la unitat de convivéncia, adult o menor, que assumeix responsabilitats familiars que es considera que es troben per sobre de les seves possibilitats. Aquí quedaria inclés l'alumnatembarés adolescent Amb menors cal tenir en compte: l'alumnatactivitat causant de la sobre-responsabilitat- La maduresa i edat de l'infant. - La maduresa de la familia (si la familia vetlla o no per minimitzar l'impacte de la responsabilitat atribuéda al menor).- Si disposa de la supervisió déalgun adult de l'entorn de proximitat.- El temps que séexposa a la sobre-responsabilitat"
													,entornFamiliar,0.43d,0.77d,2.5d));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Relacionat a fets concrets sobrevinguts.",baixaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"La situació afecta a un adult amb mancances però amb capcitat de donar resposta a la situació mitjanéant el suport necessari. La situació afecta a un/a menor major de 16 anys, sense que afecti greument a la seva integritat piscològica i amb possiblitat de canvis i millora.",moderadaGravetat));		
																
		criteriService.save(new Criteri ("Menys de 2 mesos",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Entre 2 i 6 mesos",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Més de 6 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Afectacié greu a menors o persones dependents. La situació afecta a un adult que no la reconeix ni accepta el suport necessari.",altaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));				
																
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Dificultats en les relacions familiars (F.7)","Persona que presenta dificultats de relacié i comportaments agressius amb un o més membres de la unitat de convivéncia.",entornFamiliar,0.43d,0.77d,2.5d));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Fets molt concrets, amb reconeixement i voluntat de canvi. No instaurat en la dinémica familiar. Sense infants en el nucli de convivéncia.",baixaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Instaurat en la dinémica familiar però amb voluntat de canvi i millora. Amb infants en el nucli de convivéncia",moderadaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Afectacié greu en la dinémica familiar i la salut física/emocional dels seus membres",altaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));						
																
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca de recursos personals i autonomia en la presa de decisions(F.8)","situació en la qual la persona mostra incapacitat d'afrontar aspectes de la vida quotidiana en l'émbit de l'organitzacié familiar, econémic,  laboral, de relacié social, etc.",entornFamiliar,0.43d,0.77d,2.5d));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació recent, motivada per fets externs i reversible amb  recursos personals",baixaGravetat));		
																		
		criteriService.save(new Criteri ("Passa puntualment",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Passa de manera reiterada",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Continu",puntualFrequencia,riscRisc,frequenciaGravetat));							
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació  reversible amb suport extern",moderadaGravetat));		
																		
		criteriService.save(new Criteri ("Passa puntualment",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Passa de manera reiterada",continuaFrequencia,riscRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Continu",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació irreversibale i amb necessitat de suport",altaGravetat));		
																		
		criteriService.save(new Criteri ("Continu",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
																	
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Manca d'higiene o desordre al domicili (F.9)","Domicili que, degut al comportament déalgun del seus habitants, reuneix condicions déhabitabilitat però presenta un mal estat d'higiene general o uns elevats nivells de desordre i/o acumulacié d'estris o material divers, que dificulten la vida quotidiana de les persones que hi viuen. Inclou els casos vinculats a una malaltia mental (Séndrome de Diogenes o de Noé)",entornFamiliar,0.43d,0.77d,2.5d));		
											
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació puntual i reversible amb el suport adequat. Persones diagnosticades que col·laboren.",baixaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"situació reiterada amb necessitat de suport extern. Persones diagnosticades que no col·laboren",moderadaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Preséncia de menors o dependents al domicili. Greus afectacions per la integritat física de les persones. Indécis de malatia mental no diagnosticada ni en tractament",altaGravetat));		
																		
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));						
																
													
		
		Entorn entornSocial = entornService.save (Entorn.builder().descripcio("Entorn Social").ambit(ambit).build());
											
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Dificultats en les relacions socials i a la participacié a la comunitat (S.1)","Persona amb dificultats per adaptar-se al seu medi social (aéllament, no respecta els límits normatius ni socials del seu entorn).",entornSocial,0.2d,0.5d,1.5d));		
														
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Existeix algun xarxa relacional de suport tot i que es detecten dificultats per crear o mantenir noves relacions. Participa en alguns entorns puntuals però presenta una manca déhabilitats i capacitats per participar a la comunitat",baixaGravetat));		
														
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Manca de motivacié i voluntat per relacionar-se amb l'entorn comunitari. Aéllament social. Comportaments agressius en l'entorn. Els contactes sén amb un entorn relacional téxic/negatiu",moderadaGravetat));		
																
		criteriService.save(new Criteri ("Puntual. Conjuntural amb possibilitats de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Continu",continuaFrequencia,riscRisc,frequenciaGravetat));			
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Rebuig social (S.2)","Persona socialment aéllada i/o rebutjada per raons físiques,  de génere, étnia, religió, orientació sexual, conducta o qualsevol altra raé.",entornSocial,0.2d,0.5d,1.5d));		
														
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Rebuig en un sol entorn i sense afectacions a la integritats física o emocional de la personal",moderadaGravetat));		
														
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"En més déun entorn. Amb afectacions a la integritat de la persona",altaGravetat));		
														
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));					
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Addiccions (S.3)","Persona que fa un és abusiu d'alguna substéncia téxica i/o que presenta conductes addictives (joc, noves tecnologies, etc.), que deriven en problemes familiars, econémics i/o laborals, i de salut.",entornSocial,0.2d,0.5d,1.5d));		
														
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"En tractament i sense afectacions en les activitats essencials per la vida diéria ",baixaGravetat));		
																	
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense tractar o No compliment de les pautes de tractament. Afectacions no interfereixen en les activitats essencials de la vida diéria o les responsabilitats familiars  ",moderadaGravetat));		
																		
		criteriService.save(new Criteri ("De 1 a 3 vegades per setmana",continuaFrequencia,riscRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Més de 3 vegades per setmana",puntualFrequencia,alttRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Abés sever i afectacié important en les activitats essencials de la vida diéria o les responsabilitats familiars",altaGravetat));		
																	
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));				
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Desatenció institucional  (S.4)","Persones que per la seva situació legal o adminsitrativa no tenen accés a determinats recursos de l'émbit de  salut, educacié i protecció social.",entornSocial,0.25d,0.25d,1d));		
														
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Sense valoració",altaGravetat));		
														
		criteriService.save(new Criteri ("Capacitat de resolucié / legalitzacié",frequentFrequencia,riscRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Incapacitat de resolucié ",continuaFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Activitats marginals (S.5)","Treball no legalitzat, activitats marginals (Prostitució, venda ambulant il·legal, recollida de ferralla, altres).",entornSocial,0.2d,0.5d,1.5d));		
														
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Treball estable, sense coercions ni situacions de risc greu per la persona. Contacte esporédic amb la policia i/o mesures penals pendent",moderadaGravetat));		
														
		criteriService.save(new Criteri ("Sense valoració",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriService.save(new Criteri ("Sense valoració",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
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
		factorService.save(Factor.builder().descripcio("La persona és usuària d'un servei especialitzat en funció de la seva malaltia, discapacitat o situació de dependència")
				.gravetat(proteccioGravetat)
				.ambit(ambitAutonomia)
				.fc1m(-0.5d)
				.fctots(-1d)
				.build());
		factorService.save(Factor.builder().descripcio("La persona té uns hàbits saludables (alimentació, salut etc.) per a la situació de la seva salut ")
				.gravetat(proteccioGravetat)
				.ambit(ambitAutonomia)
				.fc1m(-0.25d)
				.fctots(-0.5d)
				.build());
		
		
		// Risc
		
		factorService.save(Factor.builder().descripcio("Es preveu que el dèficit d’autonomia de la persona s’agreugi en el curt termini (durant l’any) ")
				.gravetat(riscGravetat)
				.ambit(ambitAutonomia)
				.fc1m(0.25d)
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
		factorService.save(Factor.builder().descripcio("Els progenitors no poden acomplir les seves obligacions i responsabilitats envers a persones vulnerables (menors, gent gran) per causa d'addicions")
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
				.descripcio("Disposar de cotxe.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
				.descripcio("Disposar de rentadora.").build());
		factorEconomicService.save(FactorEconomic.builder().versioModel(versio)
				.descripcio("Disposar de connexió a internet.").build());
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
