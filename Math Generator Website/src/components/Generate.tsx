const arithmetic = () => {
  const operations: string[] = [" + ", " + ", " - ", " - ", " * ", " / "];
  const randomOperations: string[] = [operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)]];
  let result = "69";

  for (let i: number = 0; i < randomOperations.length; i++) {
    result += randomOperations[i];
    for (let j: number = 0; j < 3; j++) {
      result += Math.floor(Math.random() * 9 + 1).toString();
    }

  }
  
  return result;
}

const geometry = () => {
  let result = "";
  return result;
}

const algebruh = () => {
  let result = "";
  return result;
}

const calculus = () => {
  let result = "";
  return result;
}

const abstract = () => {
  let result = "";
  return result;
}

const stages: string[] = ["arithmetic", "geometry", "algebruh", "calculus", "abstract"];
const stageFunctions: string[] = [arithmetic(), geometry(), algebruh(), calculus(), abstract()];
let answers: number[] = [];

const Generate = (props: any) => {
    const stage: string = props.stage;
    let result: string = "";

    for (let i: number = 0; i < stages.length; i++) {
      if (stages[i] === stage) {
        result = stageFunctions[i];
        break;
      }
    }

    return (  
       <div>
        <p><span className="math display">\[{result}\]</span></p>

      <div className="flex">
      <form action="">
        
          <h2 id="answer-title">Select an answer: </h2>
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