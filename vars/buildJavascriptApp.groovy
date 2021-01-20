def call(Map config=[:], Closure body) {
    
    properties([parameters([
      choice(choices: [' ','1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'], description: 'test choice', name: 'EC2_start_time'), 
      choice(choices: [' ','1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'], description: 'test choice', name: 'EC2_end_time')
      ])])
    node {
        
        stage("display values") {
            echo "choice start time: ${params.EC2_start_time}"
            echo "choice end time: ${params.EC2_end_time}"
            env.START_TIME = "${params.EC2_start_time}"
            env.END_TIME = "${params.EC2_start_time}"
            input_parameters_ansible=[]
            echo "size before: ${input_parameters_ansible.size()}"
            input_parameters_ansible.add(params.EC2_start_time)
            input_parameters_ansible.add(params.EC2_end_time)
            input_parameters_ansible.add(choice(choices: [' ','1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'], description: 'test choice', name: 'EC2_start_time'))
            echo "size after: ${input_parameters_ansible.size()}"
            echo "value of 0 after: ${input_parameters_ansible[0]}"
            echo "value of 1 after: ${input_parameters_ansible[1]}"
            echo "value of 2 after: ${input_parameters_ansible[2]}"
            extra_vars = [:]
            echo "env start time: ${env.START_TIME}"
            echo "env end time: ${env.END_TIME}"
            
            if (env.START_TIME != " " && env.END_TIME != " " && env.START_TIME != env.END_TIME) {
                extra_vars.put("start_time", env.START_TIME)
                extra_vars.put("end_time", env.END_TIME)
            }
            echo "map size after: ${extra_vars.size()}"
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

def inputs_add_StartTime() {
    time_options = [ "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]
    description_start_time = "Select the start time you wish to assign from the dropdown list"
    input_parameters_ansible.add(choice(name: "EC2_start_time", choices: time_options, description: description_start_time))
    echo "inputs_add_StartTime - end"
}