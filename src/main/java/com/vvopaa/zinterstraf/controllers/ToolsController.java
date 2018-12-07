package com.vvopaa.zinterstraf.controllers;

import com.vvopaa.zinterstraf.model.json.JsonMessage;
import com.vvopaa.zinterstraf.service.converters.jackson.JsonMessageCreator;
import com.vvopaa.zinterstraf.service.impl.ProPlayerService;
import com.vvopaa.zinterstraf.service.impl.ProScheduleService;
import com.vvopaa.zinterstraf.service.impl.ProTeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://petstore.swagger.io", maxAge = 3600)
@RestController("/tools")
public class ToolsController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ToolsController.class);
  private static final String PRO_TEAMS_URL = "http://www.dota2.com/procircuit";

  private static final String ID_TEAMS_BODY = "teamsBody";
  private static final String CLASS_TEAMS = ".teamsElement";

  private static final String ID_SCHEDULE_BODY = "scheduleBody";
  private static final String CLASS_SCHEDULE = ".scheduleElement";

  private static final String ID_PLAYERS_BODY = "playersBody";
  private static final String CLASS_PLAYERS = ".playersElement";


  private final ProTeamService proTeamService;
  private final ProPlayerService proPlayerService;
  private final ProScheduleService proProScheduleService;

  @Autowired
  public ToolsController(ProTeamService proTeamService, ProPlayerService proPlayerService, ProScheduleService proProScheduleService) {
    this.proTeamService = proTeamService;
    this.proPlayerService = proPlayerService;
    this.proProScheduleService = proProScheduleService;
  }


  @RequestMapping(value = "/wow", produces={"application/json"}, method={RequestMethod.POST})
  public JsonMessage crawlProCircuit() {
    /*
    try {
      Document doc = Jsoup.connect(PRO_TEAMS_URL).get();
        List<ProTeam> teams = new ArrayList<>();
        doc.getElementById(ID_TEAMS_BODY).select(CLASS_TEAMS).forEach(teamElement-> {
          ProTeam.ProTeamBuilder teamBuilder = new ProTeam.ProTeamBuilder();
          teamElement.children().forEach(teamBuilder::buildByDomElements);
          ProTeam currentTeam = teamBuilder.build();
          if(teams.stream().noneMatch(team -> team.getName().equals(currentTeam.getName()))) {
            teams.add(currentTeam);
          }
        });

        List<ProPlayer> players = new ArrayList<>();
        doc.getElementById(ID_PLAYERS_BODY).select(CLASS_PLAYERS).forEach(playerElement-> {
          ProPlayer.ProPlayerBuilder playerBuilder = new ProPlayer.ProPlayerBuilder();
          playerElement.children().forEach(playerBuilder::buildByDomElements);
          ProPlayer player = playerBuilder.build();
          if(players.stream().noneMatch(proPlayer -> proPlayer.getName().equals(player.getName()))) {
            teams.stream().filter(team->team.getName().equals(player.getTeamName())).findFirst()
              .ifPresent(
                team -> {
                  player.setProTeam(team);
                  team.getPlayers().add(player);
                }
              );
            players.add(player);
          }
        });
        proTeamService.saveList(teams);
        proPlayerService.saveList(players);

        List<ProSchedule> proScheduleList = new ArrayList<>();
        doc.getElementById(ID_SCHEDULE_BODY).select(CLASS_SCHEDULE).forEach(teamElement-> {
          ProSchedule.ProScheduleBuilder scheduleBuilder = new ProSchedule.ProScheduleBuilder();
          teamElement.children().forEach(scheduleBuilder::buildByDomElements);
          ProSchedule proScheduleEl = scheduleBuilder.build();
          if(proScheduleList.stream().noneMatch(proSchedule -> proSchedule.getDateFromTo().equals(proScheduleEl.getDateFromTo()))) {
            proScheduleList.add(proScheduleEl);
          }
        });
        proScheduleService.saveList(proScheduleList);

    } catch (HttpStatusException httpExc) {
      LOGGER.warn(httpExc.getMessage());
    } catch (IOException e) {
      LOGGER.warn("Error while requesting page: ".concat(e.getMessage()));
    } finally {
      LOGGER.info(Scheduler.class.getName().concat(" parsed ").concat(PRO_TEAMS_URL));
    }
    */
    return JsonMessageCreator.createSimpleJsonMessage("Crawling has been done");
  }

  @RequestMapping(value = "/test", produces={"application/json"}, method={RequestMethod.POST})
  public JsonMessage crawlProCircuit(String msg) {

    return JsonMessageCreator.createSimpleJsonMessage(msg);
  }
}
