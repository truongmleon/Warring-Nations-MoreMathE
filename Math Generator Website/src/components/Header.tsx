const math: string[] = ["arithmetic", "geometry", "algebruh", "calculus", "abstract"];

const getProblem = () => {
  const stageValue = (document.getElementById("stages") as HTMLInputElement).value;

  math.forEach((value) => {
    if (value === stageValue) {
      window.location.href = value.replace(/\s+/g, '') + ".html"
    }
  });
}

const Header = () => {
  return (
    <div className="">
      <h1 className="titles">Warring Nations Math Maker</h1>

    <div className="flex">
    <label id="label" htmlFor="stages">Stage:</label>

    <select name="stages" id="stages">
      <option value="arithmetic">Arithmetic</option>
      <option value="geometry">Geometry</option>
      <option value="algebruh">Algebruh</option>
      <option value="calculus">Calculus</option>
      <option value="abstract">Abstract</option>
    </select>

    <button onClick={() => getProblem()} className="go">Go</button>
    </div>
    
    </div>
  )
}

export default Header;