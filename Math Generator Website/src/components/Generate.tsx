const arithmetic = () => {
  const operations: string[] = ["+", "-", "*", "/"];
}

const geometry = () => {
  
}

const algebruh = () => {
  
}

const calculus = () => {
  
}

const abstract = () => {
  
}

const stages: string[] = ["arithmetic", "geometry", "algebruh", "calculus", "abstract"];
const stageFunctions: void[] = [arithmetic(), geometry(), algebruh(), calculus(), abstract()];
let answers: number[] = [];

const Generate = (props: any) => {
    const stage: string = props.stage;

    for (let i: number = 0; i < stages.length; i++) {
      if (stages[i] === stage) {
        stageFunctions[i];
        break;
      }
    }

    return (  
       <div>
        <p><span className="math display">\[765456789 + 987654567\]</span></p>
         <div className="flex">
         </div>

      <div className="flex">
      <form action="">
        
          <h2>Select an answer: </h2>
          {answers[0]}<input type="radio" name="answer" value="" /> <br />
          {answers[1]}<input type="radio" name="answer" value="" /> <br />
          {answers[2]}<input type="radio" name="answer" value="" /> <br />
          {answers[3]}<input type="radio" name="answer" value="" /> <br />
      </form>
  
      <button className="go">Enter</button>
      </div>
       </div>
    )
  }
  
  export default Generate;