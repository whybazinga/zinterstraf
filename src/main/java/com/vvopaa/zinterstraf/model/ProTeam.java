package com.vvopaa.zinterstraf.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name="pro_teams")
public class ProTeam extends AbstractEntity {
  private static final String CLASS_RANK = "rankColumn";
  private static final String CLASS_TEAM_LOGO = "teamLogoColumn";
  private static final String CLASS_TEAM_NAME = "teamColumn";
  private static final String CLASS_EARNINGS = "earningsColumn";
  private static final String CLASS_POINTS = "pointsColumn";

  @Column(name="rank")
  private String rank;

  @Column(name="img")
  private String img;

  @Column(name="name")
  private String name;

  @Column(name="earnings")
  private String earnings;

  @Column(name="points")
  private String points;

  @Column(name="players")
  private String players;

  private ProTeam(ProTeamBuilder builder) {
    this.rank = builder.rank;
    this.img = builder.img;
    this.name = builder.name;
    this.earnings = builder.earnings;
    this.points = builder.points;
  }

  public static class ProTeamBuilder implements Builder {
    private String rank;
    private String img;
    private String name;
    private String earnings;
    private String points;

    public void buildByDomElements(Element domEl) {
      if(domEl.hasClass(CLASS_RANK)) {
        this.rank = domEl.text();
      } else if(domEl.hasClass(CLASS_TEAM_LOGO)) {
        this.img = domEl.firstElementSibling().attr("src");
      } else if(domEl.hasClass(CLASS_TEAM_NAME)) {
        this.name = domEl.text();
      }else if(domEl.hasClass(CLASS_EARNINGS)) {
        this.earnings = domEl.text();
      }else if (domEl.hasClass(CLASS_EARNINGS)) {
        this.points = domEl.text();
      }
    }


    @Override
    public ProTeam build() {
      return new ProTeam(this);
    }
  }
}
