import React, {useState, useEffect} from 'react'
import axios from 'axios'
import NavBar from './components/NavBar'
import TeamCard from './components/TeamCard'
import Box from '@material-ui/core/Box'
import Container from '@material-ui/core/Container'
import { createMuiTheme , makeStyles } from '@material-ui/core/styles';
import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import CircularProgress from '@material-ui/core/CircularProgress';

const herokuLink = "https://web422-api-server.herokuapp.com/";

const useStyles = makeStyles(theme => ({
  progress: {
    margin: theme.spacing(2),
  },
}));
const theme = createMuiTheme({
  palette: {
    primary: {
      main: '#3498db',
      contrastText: 'white',
    },
    }});
function TeamComponent() {
  const [Teams, getTeam] = useState([]);
  const [Employees, getEmployee] = useState([]);
  const [Projects, getProject] = useState([]);
  const [Ready, setStatus] = useState(false);
  const classes = useStyles();


  useEffect( () => {
    const fetchData = async () => {
      let fetchTeam = await axios.get(herokuLink + 'teams-raw');
      getTeam(fetchTeam.data);
      let fetchEmployee = await axios.get(herokuLink + 'employees');
      getEmployee(fetchEmployee.data);
      let fetchProject = await axios.get(herokuLink + 'projects');
      getProject(fetchProject.data);
      setStatus(true);
    };
    fetchData();
  }, []);
  return (
      <MuiThemeProvider theme={theme}>
      <Container>
        <NavBar />
          <Box display="flex"
               alignContent="flex-start"
               flexDirection="row"
               flexWrap="wrap"
          >
          { Ready ? Teams.map( team =>
                    <TeamCard
                        key={team._id}
                        Team={team}
                        Employees={Employees}
                        Projects={Projects}
                        Url={herokuLink}
                    />
                ) :(<div>
                      <CircularProgress className={classes.progress} />
                    </div>
                
              )}
            </Box>
      </Container>
    </MuiThemeProvider>
    );
}
export default TeamComponent
