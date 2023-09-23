const Abstract = () => {
  let link: string = "0; url=https://amctrivial.com/?page=";
  let year: number = 2002;
  let problem: number = 1;
  let form: any = ["A", "B"];

  year += Math.floor(Math.random() * 21);
  problem = Math.floor(Math.random() * 25) + 1;
  form = form[Math.floor(Math.random() * 2)];

  link += year + "_AMC_10" + form + "_Problems/Problem_" + problem;

  return (
    <div className="">
      <meta http-equiv="Refresh" content={link} />
      <h1 className="titles">Abstract</h1>
    </div>
  )
}

export default Abstract;