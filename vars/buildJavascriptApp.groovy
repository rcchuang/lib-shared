def call(Map config=[:], Closure body) {
    
    properties([parameters([choice(choices: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'], description: 'test choice', name: 'EC2_start_time'), booleanParam(defaultValue: true, description: 'test boolean', name: 'RunningNonStop'), string(defaultValue: 'TBD', description: 'not sure', name: 'not_used', trim: false)])])
    node {
        
        stage("display values"{
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
