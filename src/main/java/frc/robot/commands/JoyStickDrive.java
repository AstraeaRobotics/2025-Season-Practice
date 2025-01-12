// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
package frc.robot.commands.motorSpeed;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDrivebase;

public class JoyStickDrive extends Command {
  /** Creates a new JoyStickDrive. */
  private final DoubleSupplier m_motorSpeed;
  private final DoubleSupplier m_turn;
  private final TankDrivebase m_motor1;
  private final TankDrivebase m_motor2;
  private final TankDrivebase m_motor3;
  private final TankDrivebase m_motor4;

  public JoyStickDrive(TankDrivebase motor1, TankDrivebase motor2, TankDrivebase motor3, TankDriverbase motor4, DoubleSupplier turn, DoubleSupplier m_motorSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_motor1 = motor1;
    m_motor2 = motor2;
    m_motor3 = motor3;
    m_motor4 = motor4;
    this.m_motorSpeed = m_motorSpeed;
    m_turn = turn;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    curve(m_motorSpeed.getAsDouble(),m_turn.getAsDouble(),true);
    m_motor1.setSpeed(speeds.left);
    m_motor2.setSpeed(speeds.left);
    m_motor3.setSpeed(speeds.right);
    m_motor4.setSpeed(speeds.right);
  } 

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
//