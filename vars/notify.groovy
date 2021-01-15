import com.example.Constants

def call(Map config=[:]) {
    inputs_add_StartTime()
    if (config.type == "slack") {
        echo Constants.SLACK_MESSAGE
        echo config.message
    } else {
        echo Constants.EMAIL_MESSAGE
        echo config.message
    }
}

def inputs_add_StartTime() {
    input_parameters_ansible= []
    time_options = [ "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]
    description_start_time = "Select the start time you wish to assign from the dropdown list"
    input_parameters_ansible.add(choice(name: "EC2_start_time", choices: time_options, description: description_start_time))
}