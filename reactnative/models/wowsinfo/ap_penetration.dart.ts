
const _penetrationValue = 0.5561613;
const _gravity = 9.80665;
const _seaLevelTemperature = 288.15;
const _temperatureLapseRate = 0.0065;
const _seaLevelPressure = 101325.0;
const _univGasConstant = 8.31447;
const _massAir = 0.0289644;

const _cwQuadratic = 1;

const _angleInterval = 0.1;
const _step = 0.1; // time step

interface ApPenetrationInfo {
  // Define the properties of the info object here
}

interface ApPenetrationProps {
  info: ApPenetrationInfo;
  range: number;
  verticalAngle: number;
}

const ApPenetration: React.FC<ApPenetrationProps> = ({ info, range, verticalAngle }) => {
  // Implement the logic for AP Penetration calculation here

  return (
    <View>
      {/* Render any necessary UI components here */}
    </View>
  );
};

export default ApPenetration;


interface ArmorPiecingInfo {
  weight: number;
  diameter: number;
  drag: number;
  velocity: number;
  krupp: number;
}

interface ApPenetrationInfo {
  penetration: number[];
  distance: number[];
  time: number[];
}

class ApPenetrationCalculator {
  private info: ArmorPiecingInfo;
  private range: number;
  private verticalAngle: number;
  private _penetrationValue: number;
  private _step: number;
  private _seaLevelTemperature: number;
  private _temperatureLapseRate: number;
  private _seaLevelPressure: number;
  private _gravity: number;
  private _massAir: number;
  private _univGasConstant: number;
  private _cwQuadratic: number;

  constructor(info: ArmorPiecingInfo, range: number, verticalAngle: number) {
    this.info = info;
    this.range = range;
    this.verticalAngle = verticalAngle;
    this._penetrationValue = 0; // Initialize as needed
    this._step = 0.1; // Example step value
    this._seaLevelTemperature = 288.15; // Example value in Kelvin
    this._temperatureLapseRate = 0.0065; // Example value
    this._seaLevelPressure = 101325; // Example value in Pascals
    this._gravity = 9.81; // Gravity in m/s^2
    this._massAir = 0.029; // kg/mol
    this._univGasConstant = 8.314; // J/(mol·K)
    this._cwQuadratic = 0.5; // Example value
  }

  private angles(): number[] {
    // Generate angles for calculations
    return Array.from({ length: 90 }, (_, i) => (i * Math.PI) / 180);
  }

  public calculatePenetration(): ApPenetrationInfo {
    const weight = this.info.weight;
    const diameter = this.info.diameter;
    const drag = this.info.drag;
    const velocity = this.info.velocity;
    const krupp = this.info.krupp;

    const cwLinear = 100.0 + (1000.0 / 3.0) * diameter;
    const penetrationValue = this._penetrationValue * krupp / 2400.0;
    const dragConstant = 0.5 * drag * Math.pow((diameter / 2.0), 2.0) * Math.PI / weight;

    const penInfo: ApPenetrationInfo = {
      penetration: [],
      distance: [],
      time: []
    };

    for (const angle of this.angles()) {
      let vX = Math.cos(angle) * velocity;
      let vY = Math.sin(angle) * velocity;

      let x = 0.0;
      let y = 0.0;
      let t = 0.0;

      while (y >= 0) {
        x += vX * this._step;
        y += vY * this._step;

        const temperature = this._seaLevelTemperature - this._temperatureLapseRate * y;
        const pressure = this._seaLevelPressure *
          Math.pow(
            1 - this._temperatureLapseRate * y / this._seaLevelTemperature,
            this._gravity * this._massAir / (this._univGasConstant * this._temperatureLapseRate),
          );
        const density = pressure * this._massAir / (this._univGasConstant * temperature);

        vX -= (this._step * dragConstant * density) *
          (this._cwQuadratic * Math.pow(vX, 2.0) + cwLinear * vX);
        vY -= (this._step * this._gravity) -
          (this._step * dragConstant * density) *
          (this._cwQuadratic * Math.pow(vY, 2.0) + cwLinear * Math.abs(vY)) * Math.sign(vY);

        t += this._step;
      }

      if (x > this.range * 1.1) break;

      const distance = Math.sqrt(Math.pow(vX, 2.0) + Math.pow(vY, 2.0));
      const penetration = penetrationValue *
        Math.pow(distance, 1.1) *
        Math.pow(weight, 0.55) /
        Math.pow(diameter * 1000, 0.65);
      const impactAngle = Math.atan(Math.abs(vY) / Math.abs(vX));

      penInfo.penetration.push(penetration * Math.cos(impactAngle));
      penInfo.distance.push(x);
      penInfo.time.push(t / 3.0);
    }

    return penInfo;
  }
}


const verticalAngle = 90; // Example value
const angleInterval = 10; // Example value

const angles = (): Iterable<number> => {
  const count = Math.ceil(verticalAngle / angleInterval);
  return Array.from({ length: count }, (_, i) => (i * angleInterval * Math.PI) / 180);
};

const AngleComponent: React.FC = () => {
  const angleList = Array.from(angles());

  return (
    <View>
      {angleList.map((angle, index) => (
        <Text key={index}>{angle}</Text>
      ))}
    </View>
  );
};

export default AngleComponent;


interface ApPenetrationInfoProps {
  penetration: number[];
  distance: number[];
  time: number[];
}

const ApPenetrationInfo: React.FC<ApPenetrationInfoProps> = () => {
  const penetration: number[] = [];
  const distance: number[] = [];
  const time: number[] = [];

  return (
    <View>
      <Text>Penetration Info</Text>
      <Text>Penetration: {penetration.join(', ')}</Text>
      <Text>Distance: {distance.join(', ')}</Text>
      <Text>Time: {time.join(', ')}</Text>
    </View>
  );
};

export default ApPenetrationInfo;