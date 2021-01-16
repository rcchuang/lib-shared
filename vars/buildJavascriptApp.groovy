def call(Map config=[:], Closure body) {
    properties([parameters([choice(choices: time_options, description: description_start_time, name: 'EC2_start_time'), booleanParam(defaultValue: true, description: 'test boolean', name: 'RunningNonStop'), string(defaultValue: 'TBD', description: 'not sure', name: 'not_used', trim: false)])])
    
    node {
        
        stage("display values") {
            echo "choice: ${params.EC2_start_time}"
            echo "boolean: ${params.RunningNonStop}"
            echo "string: ${params.not_used}"
        }
        git url: "https://github.com/werne2j/sample-nodejs"
        stage("Install") {
            sh "npm install"
        }
        stage("Test") {
            sh "npm test"
        }
        stage("Deploy") {
            if (config.deploy) {
                sh "npm publish"
            }
        }
        body()
    }
}

def user_input() { 
    input_parameters_ansible=[] 
    time_options = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23']
    description_start_time = "Select the start time you wish to assign from the dropdown list"
    properties([parameters([choice(choices: time_options, description: description_start_time, name: 'EC2_start_time'), booleanParam(defaultValue: true, description: 'test boolean', name: 'RunningNonStop'), string(defaultValue: 'TBD', description: 'not sure', name: 'not_used', trim: false)])])
    
}
def inputs_add_StartTime() {
    time_options = [ "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]
    description_start_time = "Select the start time you wish to assign from the dropdown list"
    input_parameters_ansible.add(choice(name: "EC2_start_time", choices: time_options, description: description_start_time))
}