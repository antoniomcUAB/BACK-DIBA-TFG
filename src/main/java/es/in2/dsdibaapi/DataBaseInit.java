package es.in2.dsdibaapi;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import es.in2.dsdibaapi.model.Municipi;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Rol;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.AmbitService;
import es.in2.dsdibaapi.service.CriteriService;
import es.in2.dsdibaapi.service.EntornService;
import es.in2.dsdibaapi.service.FactorEconomicService;
import es.in2.dsdibaapi.service.FactorService;
import es.in2.dsdibaapi.service.FrequenciaGravetatService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.MunicipiService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.RolService;
import es.in2.dsdibaapi.service.SituacioSocialService;
import es.in2.dsdibaapi.service.VersioModelService;
import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
@ConditionalOnProperty(name = "api.db-init", havingValue = "true")
public class DataBaseInit implements CommandLineRunner {
	
	@Autowired
	private AmbitService ambitService; 
	
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
	private RolService rolService;
	
	@Autowired
	private MunicipiService municipiService;
	

	@Autowired
	private VersioModelService versioModelService;
	
	
	/*@Value("${add.test}")
	private Boolean addTest;*/

	@Override
	public void run(String... args) throws Exception {
		
		VersioModel versio = versioModelService.save(VersioModel.builder().versio("111/AAAA/00").data(new Date()).build());
		
		Frequencia ocasionalFrequencia= frequenciaService.save(Frequencia.builder().DESCRIPCIO("Ocasional").build());
		Frequencia frequentFrequencia=frequenciaService.save(Frequencia.builder().DESCRIPCIO("Freqüent").build());
		Frequencia continuaFrequencia=frequenciaService.save(Frequencia.builder().DESCRIPCIO("Continua").build());
		Frequencia puntualFrequencia=frequenciaService.save(Frequencia.builder().DESCRIPCIO("Puntual").build());
		Frequencia senseFrequencia=frequenciaService.save(Frequencia.builder().DESCRIPCIO("Sense valoració").build());		
		
		Risc senseRisc = riscService.save(Risc.builder().DESCRIPCIO("Sense Valoració").value(0).build());
		Risc vulnerabilitatRisc = riscService.save(Risc.builder().DESCRIPCIO("Vulnerabilitat").value(1).build());
		Risc riscRisc = riscService.save(Risc.builder().DESCRIPCIO("Risc").value(2).build());
		Risc alttRisc = riscService.save(Risc.builder().DESCRIPCIO("Alt Risc").value(3).build());
		
		Gravetat baixaGravetat = gravetatService.save(Gravetat.builder().DESCRIPCIO("Baixa").build());		
		Gravetat moderadaGravetat= gravetatService.save(Gravetat.builder().DESCRIPCIO("Moderada").build());
		Gravetat  altaGravetat=gravetatService.save(Gravetat.builder().DESCRIPCIO("Alta").build());
		Gravetat proteccioGravetat=gravetatService.save(Gravetat.builder().DESCRIPCIO("Protecció").build());
		Gravetat riscGravetat=gravetatService.save(Gravetat.builder().DESCRIPCIO("Risc").build());
		
		rolService.save(Rol.builder().DESCRIPCIO("Gerent").build());
		rolService.save(Rol.builder().DESCRIPCIO("Tècnic").build());
		
		municipiService.save(Municipi.builder().DESCRIPCIO("Barcelona").build());
		municipiService.save(Municipi.builder().DESCRIPCIO("Tarragona").build());
		municipiService.save(Municipi.builder().DESCRIPCIO("LLeida").build());
		
		Ambit ambit = ambitService.save (Ambit.builder().DESCRIPCIO("Autonomia").vulnerabilitat(4d).risc(7d).build());
		
		Entorn entornAutonomia = entornService.save (Entorn.builder().DESCRIPCIO("Autonomia").ambit(ambit).build());
		
		
		
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
		
		
		ambit = ambitService.save(Ambit.builder().DESCRIPCIO("MATERIAL I INSTRUMENTAL").vulnerabilitat(3d).risc(7d).build());				
				
		Entorn entornHabitatge = entornService.save (Entorn.builder().DESCRIPCIO("Entorn habitatge").ambit(ambit).build());
								
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

		
		Entorn entornEconomic = entornService.save (Entorn.builder().DESCRIPCIO("Entorn Económic").ambit(ambit).build());

		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Sense ingressos (E.1)","Unitats familiars que no tenen ingressos o que no els acrediten de forma estable o regular. Es proposa Comptabilitzar en aquesta categoria aquelles unitats familiars on séhan identificat més de 6 étems dels qüestionari."
												,entornEconomic,1d,2d,3d));
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Disposen de xarxa o recursos que proporcionen  suport econémic. No han déafrontar pagament de l'habitatge habitual",moderadaGravetat));
		criteriService.save(new Criteri ("De forma puntual/sobrevinguda i amb capacitat de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma continua/Crónica",puntualFrequencia,alttRisc,frequenciaGravetat));	
							
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"No disposen de xarxa o recursos de suport econémic",moderadaGravetat));	
									
		criteriService.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
							
		situacioSocial = situacioSocialService.save(new SituacioSocial (versio,"Ingressos insuficients (E.2)","Unitats familiars que malgrat tenir uns ingressos tenen dificultats per cobrir determinades necessitats  dels seus integrants."
												,entornEconomic,1d,2d,3d));
								
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
										
		Entorn entornLaboral = entornService.save (Entorn.builder().DESCRIPCIO("Entorn Laboral").ambit(ambit).build());
											
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
											
											
		ambit = ambitService.save(Ambit.builder().DESCRIPCIO("RELACIONAL").build());						
											
		Entorn entornEscolar = entornService.save (Entorn.builder().DESCRIPCIO("Entorn Escolar").ambit(ambit).build());
											
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
		criteriService.save(new Criteri ("Fet puntual o en un peròode de méxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"S'identifiquen  situacions d'agressions verbals per part de grup reduét",moderadaGravetat));					
		criteriService.save(new Criteri ("Fet puntual o en un peròode de méxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,alttRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatService.save(new FrequenciaGravetat(situacioSocial,"Agressions físiques o agressions verbals provinents de gran grup",altaGravetat));					
		criteriService.save(new Criteri ("Fet puntual o en un peròode de méxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriService.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,alttRisc,frequenciaGravetat));
											
		Entorn entornFamiliar = entornService.save (Entorn.builder().DESCRIPCIO("Entorn Familiar").ambit(ambit).build());
				
											
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
																
													
		
		Entorn entornSocial = entornService.save (Entorn.builder().DESCRIPCIO("Entorn Social").ambit(ambit).build());
											
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

		
		
		// Proteccio
		
		factorService.save(new Factor("La malaltia causa del déficit d'autonomia esté diagnosticada i en tractament",proteccioGravetat,entornAutonomia));
		factorService.save(new Factor("La persona té reconeguts el grau de discapacitat i / o dependéncia",proteccioGravetat,entornAutonomia));
		factorService.save(new Factor("La persona és usuéria d'un servei, recurs o prestacié  en funcié de la seva malaltia, discapacitat o situació de dependéncia",proteccioGravetat,entornAutonomia));
		factorService.save(new Factor("Hi ha una xarxa familiar i / o social de suport implicada en la cura",proteccioGravetat,entornAutonomia));
		factorService.save(new Factor("La persona segueix amb regularitat el tractament médic i/o té un hébits saludables per la seva situació de salut",proteccioGravetat,entornAutonomia));

		// Risc
		
		factorService.save(new Factor("Es preveu que el déficit d'autonomia de la persona séagreugi en un curt termini (durant l'any)",riscGravetat,entornAutonomia));
		factorService.save(new Factor("La malaltia causa del déficit d'autonomia no esté diagnosticada i en tractament",riscGravetat,entornAutonomia));
		factorService.save(new Factor("La persona no segueix amb regularitat el tractament médic prescrit i/o no té uns hébits saludables per la seva situació de salut",riscGravetat,entornAutonomia));
		factorService.save(new Factor("No existeix una xarxa familiar i / o social que séimpliqui en la cura",riscGravetat,entornAutonomia));
		factorService.save(new Factor("Cuidador / a habitual en risc o en situació de claudicacié",riscGravetat,entornAutonomia));
		factorService.save(new Factor("La situació i/o caracteréstiques del domicili agreugen el déficit d'autonomia  de les persones que hi viuen",riscGravetat,entornAutonomia));
		factorService.save(new Factor("La persona amb déficit d'autonomia té responsabilitats/carregues familiars",riscGravetat,entornAutonomia));
		
		
		// Proteccio
		factorService.save(new Factor("Una o més persones de la familia disposa d'ingressos estables (pensions, prestacions, rendes, etc.).",proteccioGravetat,entornHabitatge));
		factorService.save(new Factor("Una o més persones de la familia disposa d'ingressos estables (pensions, prestacions, rendes, etc.).",proteccioGravetat,entornEconomic));
		factorService.save(new Factor("Una o més persones de la familia disposa d'estalvis",proteccioGravetat,entornHabitatge));
		factorService.save(new Factor("Una o més persones de la familia disposa d'estalvis",proteccioGravetat,entornEconomic));
		factorService.save(new Factor("Una o més persones de la familia disposa de patrimoni a part de la primera residéncia",proteccioGravetat,entornHabitatge));
		factorService.save(new Factor("Una o més persones de la familia disposa de patrimoni a part de la primera residéncia",proteccioGravetat,entornEconomic));
		factorService.save(new Factor("Una o més persones de la familia disposa de treball estable.",proteccioGravetat,entornHabitatge));
		factorService.save(new Factor("Una o més persones de la familia disposa de treball estable.",proteccioGravetat,entornEconomic));
		factorService.save(new Factor("Existeix xarxa familiar o relacional disposada a oferir suport econémic",proteccioGravetat,entornHabitatge));
		factorService.save(new Factor("Existeix xarxa familiar o relacional disposada a oferir suport econémic",proteccioGravetat,entornEconomic));
		factorService.save(new Factor("En cas de separacié o divorci hi ha conveni regulador i es compleixen els seus termes",proteccioGravetat,entornHabitatge));
		factorService.save(new Factor("En cas de separacié o divorci hi ha conveni regulador i es compleixen els seus termes",proteccioGravetat,entornEconomic));
		factorService.save(new Factor("La persona té una bona predisposicié per formar-se i/o treballar",proteccioGravetat,entornHabitatge));
		factorService.save(new Factor("La persona té una bona predisposicié per formar-se i/o treballar",proteccioGravetat,entornEconomic));
		factorService.save(new Factor("Es disposa déun habitatge habitual pel qual no séha déassumir cap despesa o és un habitatge social",proteccioGravetat,entornHabitatge));
		factorService.save(new Factor("Es disposa déun habitatge habitual pel qual no séha déassumir cap despesa o és un habitatge social",proteccioGravetat,entornEconomic));


		// Risc
		factorService.save(new Factor("Existeix un impagament de pensions de manutencié i la corresponent demanda judicial",riscGravetat,entornHabitatge));
		factorService.save(new Factor("Existeix un impagament de pensions de manutencié i la corresponent demanda judicial",riscGravetat,entornEconomic));
		factorService.save(new Factor("Existeixen responsabilitats i cérregues familiars que agreugen la manca de recursos materials/instrumentals",riscGravetat,entornHabitatge));
		factorService.save(new Factor("Existeixen responsabilitats i cérregues familiars que agreugen la manca de recursos materials/instrumentals",riscGravetat,entornEconomic));
		factorService.save(new Factor("Hi ha un endeutament creixent i inassolible amb els recursos disponibles",riscGravetat,entornHabitatge));
		factorService.save(new Factor("Hi ha un endeutament creixent i inassolible amb els recursos disponibles",riscGravetat,entornEconomic));
		
		
		// Proteccio
		factorService.save(new Factor("La persona mostra una vinculacié relacional positiva amb la familia extensa o les seves xarxes déamics i relacions",proteccioGravetat,entornEscolar));
		factorService.save(new Factor("La persona mostra una vinculacié relacional positiva amb la familia extensa o les seves xarxes déamics i relacions",proteccioGravetat,entornFamiliar));
		factorService.save(new Factor("La persona esté vinculada i participa activament amb la comunitat, entitats o associacions del seu entorn",proteccioGravetat,entornEscolar));
		factorService.save(new Factor("La persona esté vinculada i participa activament amb la comunitat, entitats o associacions del seu entorn",proteccioGravetat,entornFamiliar));
		factorService.save(new Factor("Existeix xarxa familiar o relacional disposada a oferir suport i acompanyament",proteccioGravetat,entornEscolar));
		factorService.save(new Factor("Existeix xarxa familiar o relacional disposada a oferir suport i acompanyament",proteccioGravetat,entornFamiliar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a manté relacié afectiva amb els adults de referéncia i iguals",proteccioGravetat,entornEscolar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a manté relacié afectiva amb els adults de referéncia i iguals",proteccioGravetat,entornFamiliar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a mostra una autonomia personal adequada per a la seva edat",proteccioGravetat,entornEscolar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a mostra una autonomia personal adequada per a la seva edat",proteccioGravetat,entornFamiliar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a participa en activitats fora de l'entorn familiar o escolar",proteccioGravetat,entornEscolar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a participa en activitats fora de l'entorn familiar o escolar",proteccioGravetat,entornFamiliar));
		factorService.save(new Factor("La persona/familia és usuéria de recursos/serveis adreéats a cobrir la necessitat en l'àmbit relacional (escolar, familiar o comunitari) ",proteccioGravetat,entornEscolar));
		factorService.save(new Factor("La persona/familia és usuéria de recursos/serveis adreéats a cobrir la necessitat en l'àmbit relacional (escolar, familiar o comunitari) ",proteccioGravetat,entornFamiliar));


		// Risc
		factorService.save(new Factor("Existeix una xarxa social (veéns, amics, familia extensa..) que exerceix una influéncia negativa",riscGravetat,entornEscolar));
		factorService.save(new Factor("Existeix una xarxa social (veéns, amics, familia extensa..) que exerceix una influéncia negativa",riscGravetat,entornFamiliar));
		factorService.save(new Factor("Sospita o malaltia mental diagnosticada que agreugen la situació ",riscGravetat,entornEscolar));
		factorService.save(new Factor("Sospita o malaltia mental diagnosticada que agreugen la situació ",riscGravetat,entornFamiliar));
		factorService.save(new Factor("Existeix una problemética de salut no diagnosticada que agreuja la situació",riscGravetat,entornEscolar));
		factorService.save(new Factor("Existeix una problemética de salut no diagnosticada que agreuja la situació",riscGravetat,entornFamiliar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a no manté relacié afectiva amb els adults de referéncia i iguals",riscGravetat,entornEscolar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a no manté relacié afectiva amb els adults de referéncia i iguals",riscGravetat,entornFamiliar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a no mostra una autonomia personal adequada per a la seva edat",riscGravetat,entornEscolar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a no mostra una autonomia personal adequada per a la seva edat",riscGravetat,entornFamiliar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a no participa en activitats fora de l'entorn familiar o escolar",riscGravetat,entornEscolar));
		factorService.save(new Factor("En el cas d'inféncia que el nen / a no participa en activitats fora de l'entorn familiar o escolar",riscGravetat,entornFamiliar));
		
		

		factorEconomicService.save(new FactorEconomic(versio,"Pagar les factures d'habitatge o serveis públics."));
		factorEconomicService.save(new FactorEconomic(versio,"Mantenir la llar adequadament calenta."));
		factorEconomicService.save(new FactorEconomic(versio,"Assumir despeses inesperades."));
		factorEconomicService.save(new FactorEconomic(versio,"Menjar carn o les proteénes de forma regular."));
		factorEconomicService.save(new FactorEconomic(versio,"Anar de vacances."));
		factorEconomicService.save(new FactorEconomic(versio,"Disposar de cotxe."));
		factorEconomicService.save(new FactorEconomic(versio,"Disposar de rentadora."));
		factorEconomicService.save(new FactorEconomic(versio,"Disposar de connexió a internet."));
		factorEconomicService.save(new FactorEconomic(versio,"Disposar de teléfon móbil"));
		
		ambit = ambitService.save(Ambit.builder().DESCRIPCIO("Globalitat del cas").build());
		
		Entorn entorn = entornService.save (Entorn.builder().DESCRIPCIO("Globalitat del cas").ambit(ambit).build());
		
		factorService.save(new Factor("La persona i / o familia té consciencia i accepta  les dificultats / malaltia causants de la seva situació",proteccioGravetat,entorn));
		factorService.save(new Factor("La persona i / o familia accepta el suport i l'orientació professional",proteccioGravetat,entorn));
		factorService.save(new Factor("La persona coneix i té una actitud proactiva i participativa envers el sistema i les xarxes de protecció",proteccioGravetat,entorn));
		factorService.save(new Factor("La persona i / o familia no té consciencia i/o no accepta  les dificultats/malaltia causants de la seva situació",riscGravetat,entorn));
		factorService.save(new Factor("La persona i / o familia no accepta el suport i l'orientació professional",riscGravetat,entorn));
		factorService.save(new Factor("La persona no té una actitud activa i ni participativa envers el sistema i les xarxes de protecció i/o té una posicionament victimista respecte els sistemes de protecció",riscGravetat,entorn));
		factorService.save(new Factor("Composicié familiar vulnerable (monoparental, families nombroses de categoria especial)",riscGravetat,entorn));
		
		/*
		log.info("PROVA ACCENTS: áéíó");
		
		log.info("Default Locale:   " + Locale.getDefault());
	      log.info("Default Charset:  " + Charset.defaultCharset());
	      log.info("file.encoding;    " + System.getProperty("file.encoding"));
	      log.info("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));
	      log.info("Default Encoding: " + getEncoding());
		*/
	/*	if (addTest) {
			log.info ("===========    ADD TEST    ==========");
			
			Persona persona1 = personaRepository.save(new Persona ("Persona1","APE1","APE2"));
			Persona persona2 = personaRepository.save(new Persona ("Persona2","APE1","APE2"));
			
			Expedient exp =expedientRepository.save(new Expedient (11111l, "Professional", "Test1", new Date (),"ddfd",new HashSet<Persona>() {{
	            add(persona1);
	            add(persona2);
	        }},2l));
			
			
			
			Diagnostic d = new Diagnostic (exp,entornAutonomia,situacioSocial,vulnerabilitatRisc,ocasionalFrequencia,baixaGravetat,true);		
			d=diagnosticRepository.save(d);
			
			d = new Diagnostic (exp,entornAutonomia,situacioSocial,vulnerabilitatRisc,ocasionalFrequencia,baixaGravetat,false);
			d.setPersona(persona1);
			d=diagnosticRepository.save(d);
			
			contextualitzacioRepository.save(new Contextualitzacio(true,false,factorTest,persona1,exp));
			contextualitzacioRepository.save(new Contextualitzacio(false,false,factorTest2,persona2,exp));
		}*/
		
	}
	
	public static String getEncoding()
	   {
	      final byte [] bytes = {'D'};
	      final InputStream inputStream = new ByteArrayInputStream(bytes);
	      final InputStreamReader reader = new InputStreamReader(inputStream);
	      final String encoding = reader.getEncoding();
	      return encoding;
	   }
	
	
	
}