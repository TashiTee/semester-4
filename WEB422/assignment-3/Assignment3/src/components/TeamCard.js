import React from 'react'
import axios from "axios";
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import ListItemText from '@material-ui/core/ListItemText';
import { makeStyles } from '@material-ui/core/styles';
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import CardContent from "@material-ui/core/CardContent";
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
import Popover from '@material-ui/core/Popover';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles(theme => ({
    formControl: {
        maxWidth: '100%',
        minWidth: 150,
        marginBottom: theme.spacing(3)
    },
    card: {
        width: "20vw",
        minWidth: "150px",
        margin: theme.spacing(1),
    },
    SaveButton: {
        padding: theme.spacing(2),

    },
    typography: {
        padding: theme.spacing(2)
    }
}));

function TeamCard(props) {
    const classes = useStyles();

    const [TeamMembers, setTeamMembers] = React.useState(props.Team.Employees);

    const [TeamLead, setTeamLead] = React.useState(props.Team.TeamLead);

    let asignedProjects = props.Team.Projects.map(assignedID => props.Projects.find(proj => proj._id === assignedID));

    const [Projects, setProjects] = React.useState(asignedProjects);

    const [anchorEl, setAnchorEl] = React.useState(null);

    const [SavePopoverResponse, setSavePopoverState] = React.useState("Loading");
    function handleTeamLeadChange(event) {
        setTeamLead(event.target.value)
    }
    function handleTeamMemberChange(event) {
        setTeamMembers(event.target.value)
    }
    function handleProjectChange(event) {
        setProjects(event.target.value)
    }
    function handlePopoverClose(event) {
        setAnchorEl(null);
    }
    const open = Boolean(anchorEl);

    const id = open ? 'save-popover' : null;

    function handleSave(event) {
        setAnchorEl(event.currentTarget);
        const updateData = async () => {
            await axios.put(props.Url + 'team/' + props.Team._id,
                {
                    Projects,
                    Employees: TeamMembers,
                    TeamLead
                })
                .then(response => setSavePopoverState(response.data.message));
        };
        updateData();
    }

    function EmployeeFullName(id) {
        if (Array.isArray(id)) return id.map(_id => props.Employees.find(emp => emp._id === _id)).map(employee => employee.FirstName + ' ' +  employee.LastName).join(',');
        let foundEmployee = props.Employees.find(emp => emp._id === id);
        if (foundEmployee) return foundEmployee.FirstName + ' ' + foundEmployee.LastName;
        return `Employee does not exist`;
    }

    function ProjectName(id) {
        if (Array.isArray(id)) {
            let asignedProjects = id.map((asignedproj => props.Projects.find(proj => proj._id === asignedproj._id)));
            if (asignedProjects){
                 return asignedProjects.map(asignedproj => asignedproj.ProjectName).join(',');
            }
        } else {
            let asignedProject = props.Projects.find(proj => proj._id === id);
            if (asignedProject) {
                return asignedProject.ProjectName;
            }
        }
    }
    return (
        <Card className={classes.card}>
            <CardHeader title={props.Team.TeamName}
                        action={
                            <React.Fragment>
                            <Button
                                aria-describedby={id}
                                onClick={handleSave}
                                variant="contained"
                                color="primary"
                                className={classes.button}
                            >
                                Save
                            </Button>
                                <Popover
                                    id={id}
                                    open={open}
                                    anchorEl={anchorEl}
                                    onClose={handlePopoverClose}
                                    anchorOrigin={{
                                        vertical: 'bottom',
                                        horizontal: 'left',
                                    }}
                                    transformOrigin={{
                                        vertical: 'top',
                                        horizontal: 'left',
                                    }}
                                    >
                                    <Typography className={classes.typography} gutterBottom>{SavePopoverResponse}</Typography>
                                </Popover>
                            </React.Fragment>
                        }>
            </CardHeader>
            <CardContent>
                <FormControl className={classes.formControl}>
                    <InputLabel htmlFor="select-teamLead">Team Lead</InputLabel>
                        <Select
                            id="select-teamLead"
                            autoWidth={true}
                            value={TeamLead}
                            onChange={handleTeamLeadChange}
                            input={<Input />}
                            renderValue={selected => EmployeeFullName(selected)}
                        >
                            {
                                props.Employees.map(emp =>
                                    <MenuItem key={emp._id} value={emp._id}>
                                        <ListItemText primary={EmployeeFullName(emp._id)} />
                                    </MenuItem>)
                            }
                        </Select>
                </FormControl>
                <FormControl className={classes.formControl}>
                    <InputLabel htmlFor="select-teamMem">Team Members</InputLabel>
                        <Select
                            id="select-teamMem"
                            multiple
                            autoWidth={true}
                            value={TeamMembers}
                            onChange={handleTeamMemberChange}
                            input={<Input />}
                            renderValue={selected => EmployeeFullName(selected)}
                        >
                            {props.Employees.map(emp =>
                                <MenuItem key={emp._id} value={emp._id}>
                                    <ListItemText primary={EmployeeFullName(emp._id)} />
                                </MenuItem>
                            )}
                        </Select>
                </FormControl>
                <FormControl className={classes.formControl}  >
                    <InputLabel htmlFor="select-project">Projects</InputLabel>
                        <Select
                            id="select-project"
                            autoWidth={true}
                            value={Projects}
                            onChange={handleProjectChange}
                            input={<Input id="name-native-disabled" />}
                            renderValue={selected => ProjectName(selected)}
                        >
                            {Projects.map(proj =>
                                <MenuItem key={proj._id} value={proj._id}>
                                    <ListItemText primary={ProjectName(proj._id)} />
                                </MenuItem>
                            )}
                        </Select>
                </FormControl>
            </CardContent>
        </Card>
    );
}
export default TeamCard
