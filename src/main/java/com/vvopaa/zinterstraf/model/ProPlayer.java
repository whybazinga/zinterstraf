package com.vvopaa.zinterstraf.model;

import com.vvopaa.zinterstraf.util.FunctionUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ProPlayer extends AbstractEntity {

  @Column
  private Integer rank;

  @Column(unique = true)
  private String name;

  @ManyToOne
  @JoinColumn(name = "pro_team_id")
  private ProTeam proTeam;

  @Column
  private Integer points;

  @Transient
  private String teamName;

  private ProPlayer(ProPlayerBuilder builder) {
    this.rank = builder.rank;
    this.name = builder.name;
    this.points = builder.points;
    this.teamName = builder.teamName;
  }

  @Override
  public String toString() {
    return "ProPlayer{" +
      "name='" + name + '\'' +
      ", teamName='" + teamName + '\'' +
      '}';
  }

  public static class ProPlayerBuilder implements DomBuilder {
    private static final String CLASS_RANK = "rankColumn";
    private static final String CLASS_PLAYER_NAME = "playerNameColumn";
    private static final String CLASS_TEAM_NAME = "teamColumn";
    private static final String CLASS_POINTS = "pointsColumn";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProTeam.ProTeamBuilder.class);

    private Integer rank;
    private String name;
    private Integer points;
    private String teamName;

    @Override
    public void buildByDomElements(Element domEl) {
      try {
        FunctionUtil.getInstance()
          .runFunctionIfTrue(domEl.hasClass(CLASS_RANK), () -> this.rank = Integer.parseInt(domEl.text()))
          .runFunctionIfTrue(domEl.hasClass(CLASS_PLAYER_NAME), () -> this.name = domEl.text())
          .runFunctionIfTrue(domEl.hasClass(CLASS_POINTS), () -> this.points = Integer.parseInt(domEl.text()))
          .runFunctionIfTrue(domEl.hasClass(CLASS_TEAM_NAME), () -> this.teamName = domEl.text());
      } catch (NullPointerException ex) {
        LOGGER.info(DomBuilder.TXT_WRITTEN);
      }
    }


    @Override
    public ProPlayer build() {
      return new ProPlayer(this);
    }
  }
}
