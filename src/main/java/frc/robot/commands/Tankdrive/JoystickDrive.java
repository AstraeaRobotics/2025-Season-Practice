// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Tankdrive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.TankDrivebase;

public class JoystickDrive extends Command {
  /** Creates a new JoystickDrive. */

  private final TankDrivebase m_drivebase;
  private final DoubleSupplier m_yAxis;
  private final DoubleSupplier m_xAxis;

  private final double m_deadband = 0.05;


  public JoystickDrive(TankDrivebase drivebase, DoubleSupplier y, DoubleSupplier x) {
    m_drivebase = drivebase;
    m_yAxis = y;
    m_xAxis = x;

    addRequirements(drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //adding deadband
    double ySpeed = m_yAxis.getAsDouble();
    if(Math.abs(ySpeed) < m_deadband){ ySpeed = 0; }

    double xSpeed = m_xAxis.getAsDouble();
    if(Math.abs(xSpeed) < m_deadband){ xSpeed = 0; }

    m_drivebase.curve(ySpeed, xSpeed, false);
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
