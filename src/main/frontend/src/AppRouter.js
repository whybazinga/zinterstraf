import React from 'react';
import {Route, Switch} from 'react-router-dom'

import {userRoleTypes} from "./constants/userRoleTypes";
import {RoleRoute} from "./components/roleRoute/RoleRoute";
import SwaggerUiPage from "./containers/swagger/Swagger";
import EmptyPage from "./containers/empty/Empty";
import LoginPage from "./containers/login/Login";
import RegisterPage from "./containers/register/Register";
import StartPage from "./containers/start/Start";


export const routerUrls = Object.freeze({
  home: {url: '/', name: 'Home', role: userRoleTypes.noRole, page: StartPage, exact: true},
  matches: {url: '/matches', name: 'Matches', role: userRoleTypes.noRole, page: false, exact: false},
  results: {url: '/results', name: 'Results', role: userRoleTypes.noRole, page: false, exact: false},
  tables: {url: '/tables', name: 'Tables', role: userRoleTypes.noRole, page: false, exact: false},
  teams: {url: '/teams', name: 'Teams', role: userRoleTypes.noRole, page: false, exact: false},
  social: {url: '/social', name: 'Social', role: userRoleTypes.noRole, page: false, exact: false},
  players: {url: '/players', name: 'Players', role: userRoleTypes.noRole, page: false, exact: false},
  broadcast: {url: '/broadcast', name: 'Broadcast', role: userRoleTypes.noRole, page: false, exact: false},
  dashboard: {url: '/dashboard', name: 'Dashboard', role: userRoleTypes.noRole, page: false, exact: false},
  playerStats: {url: '/player-stats', name: 'Player Stats', role: userRoleTypes.noRole, page: false, exact: false},
  teamStats: {url: '/team-stats', name: 'Team Stats', role: userRoleTypes.noRole, page: false, exact: false},
  transfers: {url: '/transfers', name: 'Transfers', role: userRoleTypes.noRole, page: false, exact: false},
  partners: {url: '/partners', name: 'Partners', role: userRoleTypes.noRole, page: false, exact: false},
  about: {url: '/about', name: 'About', role: userRoleTypes.noRole, page: StartPage, exact: false},
  publications: {url: '/publications', name: 'Publications', role: userRoleTypes.noRole, page: false, exact: false},
  contact: {url: '/contact', name: 'Contact', role: userRoleTypes.noRole, page: false, exact: false},
  login: {url: '/login', name: 'Log in', role: userRoleTypes.noRole, page: LoginPage, exact: false},
  register: {url: '/register', name: 'Register', role: userRoleTypes.noRole, page: RegisterPage, exact: true},
  swagger: {url: '/swagger-ui', name: 'Swagger', role: userRoleTypes.adminRole, page: SwaggerUiPage, exact: true}
});

export const externalUrls = Object.freeze({
  egaTwitter: {url: 'https://www.twitter.com/', name: 'EGA on Twitter'},
  egaFacebook: {url: 'https://www.facebook.com/', name: 'EGA on Facebook'},
  egaVk: {url: 'https://www.vk.com/', name: 'EGA on VK'},
  egaInstagram: {url: 'https://www.instagram.com/', name: 'EGA on Instagram'},
  egaDiscord: {url: 'https://www.discordapp.com/', name: 'EGA on Discord'}
});

export const AppRouter = ({authUser}) => {

  return(
    <main className="content">
      <Switch>
        {Object.keys(routerUrls).map((id, index) => (
          routerUrls[id].page !== false
            ? <RoleRoute key={index} exact={routerUrls[id].exact} path={routerUrls[id].url} component={routerUrls[id].page} urlRole={routerUrls[id].role} authUser={authUser} />
            : null
        ))}
        <Route component={EmptyPage} />
      </Switch>
    </main>
  )
};

