import React, { Component } from 'react';
import { Formik, Form, ErrorMessage } from 'formik';
import GetPsyData from '../../../api/getPsyData';
import AuthenticationService from '../../../api/authenticationService';


class DataInput extends Component {
    state = {  } 
    
    validate = value => {
        let error = {};
        if(value.year.length === 0)
            error.year = "Please select the year!";

        if(value.county.length === 0)
            error.county = "Please select a county!";

        if(value.numbers.length === 0)
            error.numbers = "Please provide the numbers!";

        return error;
    }

    onSubmit = values => {
        // Stay the same page as Drawmap, so don't have to re-take the token

        if(!AuthenticationService.isUserLoggedIn()) {
            alert("Invalid authenticaton!");
            return;
        }

        GetPsyData.saveData(values.year, values.county, values.numbers);
        setTimeout( (window.location.reload()), 50);
    }

    handleReset = () => {
        if(!AuthenticationService.isUserLoggedIn()) {
            alert("Invalid authenticaton!");
            return;
        }
        
        if(window.confirm("Are you sure to reset the data?")) {
            GetPsyData.resetData();
            setTimeout( (window.location.reload()), 50)
        }
    }

    render() { 
        const styleForContainer = {
            textAlign:"center"
        }
        
        return (
            <div>
                <div className="container" style={styleForContainer}>
                    <Formik initialValues = {{year:"", county:"", numbers:""}}
                        onSubmit={this.onSubmit}
                        validate={this.validate} 
                        validateOnBlur={true}
                        validateOnChange={false}          
                    >

                        {
                            formProps => (
                                <Form style={{textAlign:"center"}}>

                                    <div style ={{display:"block", marginTop:"0.5em"}}>
                                        <ErrorMessage className = "alert alert-warning" name="year" component="div" />
                                    </div>
                                    <label> Year:     
                                        <select 
                                        name="year"
                                        value={formProps.values.year}
                                        onChange = { formProps.handleChange }
                                        style={{ display: 'block', marginTop:"0.5em", textAlign:"center" }}>
                                            
                                            <option value = "" label = "choose one year"/>
                                            <option value = "2019" label = "2019"/>
                                            <option value = "2020" label = "2020"/>
                                            <option value = "2021" label = "2021"/>

                                        </select>
                                    </label>
                                    
                                    <div style ={{display:"block", marginTop:"0.5em"}}>
                                       <ErrorMessage className = "alert alert-warning" name="county" component="div" />
                                    </div>
                                    <label> County:    
                                        <select
                                        name = "county"
                                        value = {formProps.values.county}
                                        onChange={formProps.handleChange}
                                        style={{ display: 'block', marginTop:"0.5em", textAlign:"center" }}>

                                            <option value = "" label = "choose one couny"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>
                                            <option value = "?????????" label = "?????????"/>

                                        </select>

                                    </label>

                                    <div style ={{display:"block", marginTop:"0.5em"}}>
                                        <ErrorMessage className = "alert alert-warning" name="numbers" component="div" />
                                    </div>

                                    <label> Numbers:
                                        <input className="form-control" name = "numbers" onChange={formProps.handleChange}  autoComplete = "off" style={{textAlign:"center", marginTop:"0.5em"}}/>
                                    </label>

                                    <div style ={{display:"block", marginTop:"0.5em"}}></div>

                                    <button className = "btn btn-danger mt-3 mx-3" type = "button" onClick={this.handleReset}> Reset </button>
                                    <button className = "btn btn-success mt-3 mx-3" type = "submit"> Submit </button>  
                                </Form>
                            )
                        }
                    </Formik>
                </div>
            </div>
        );
    }
}
 
export default DataInput;