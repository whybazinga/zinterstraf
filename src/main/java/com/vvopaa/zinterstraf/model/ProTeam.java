package com.vvopaa.zinterstraf.model;

import com.vvopaa.zinterstraf.util.FunctionUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ProTeam extends AbstractEntity {

  @Column
  private Integer rank;

  @Column
  private String img;

  @Column(unique = true)
  private String name;

  @Column
  private String earnings;

  @Column
  private Integer points;

  @OneToMany(mappedBy = "proTeam")
  private List<ProPlayer> players = new ArrayList<>();

  private ProTeam(ProTeamBuilder builder) {
    this.rank = builder.rank;
    this.img = builder.img;
    this.name = builder.name;
    this.earnings = builder.earnings;
    this.points = builder.points;
  }

  @Override
  public String toString() {
    return "ProTeam{" +
      "name='" + name + '\'' +
      '}';
  }

  public static class ProTeamBuilder implements DomBuilder {
    private static final String CLASS_RANK = "rankColumn";
    private static final String CLASS_TEAM_LOGO = "teamLogoColumn";
    private static final String CLASS_TEAM_NAME = "teamColumn";
    private static final String CLASS_EARNINGS = "earningsColumn";
    private static final String CLASS_POINTS = "pointsColumn";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProTeamBuilder.class);

    private Integer rank;
    private String img;
    private String name;
    private String earnings;
    private Integer points;

    @Override
    public void buildByDomElements(Element domEl) {
      try {
        FunctionUtil.getInstance()
          .runFunctionIfTrue(domEl.hasClass(CLASS_RANK), () -> this.rank = Integer.parseInt(domEl.text()))
          .runFunctionIfTrue(domEl.hasClass(CLASS_TEAM_LOGO), () -> this.img = domEl.children().get(0).attr(DomBuilder.SRC_ATTRIBUTE))
          .runFunctionIfTrue(domEl.hasClass(CLASS_TEAM_NAME), () -> this.name = domEl.text())
          .runFunctionIfTrue(domEl.hasClass(CLASS_EARNINGS), () -> this.earnings = domEl.text())
          .runFunctionIfTrue(domEl.hasClass(CLASS_POINTS), () -> this.points = Integer.parseInt(domEl.text()));
      } catch (NullPointerException ex) {
        LOGGER.info(DomBuilder.TXT_WRITTEN);
      } catch (Exception e) {
        LOGGER.info(e.getMessage());
      }
    }

    @Override
    public ProTeam build() {
      return new ProTeam(this);
    }
  }
}
